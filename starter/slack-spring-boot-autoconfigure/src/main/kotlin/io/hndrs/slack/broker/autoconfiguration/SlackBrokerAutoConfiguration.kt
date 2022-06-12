package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.autoconfiguration.credentials.DefaultCredentialsProviderChain
import io.hndrs.slack.broker.command.CommandBroker
import io.hndrs.slack.broker.command.SlashCommandArgumentResolver
import io.hndrs.slack.broker.event.http.EventArgumentResolver
import io.hndrs.slack.broker.event.http.EventBroker
import io.hndrs.slack.broker.exception.SlackExceptionHandler
import io.hndrs.slack.broker.installation.InstallationBroker
import io.hndrs.slack.broker.receiver.CommandNotFoundReceiver
import io.hndrs.slack.broker.receiver.EventReceiver
import io.hndrs.slack.broker.receiver.InstallationReceiver
import io.hndrs.slack.broker.receiver.MismatchCommandReceiver
import io.hndrs.slack.broker.receiver.SL4JLoggingReceiver
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * AutoConfiguration for SlackBroker
 *
 * @property configuration The configuration properties with which you can customise the auto-configuration
 */
@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class SlackBrokerAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties) {

    /**
     * Configuration that registers the Broker-Components
     *
     * @property configuration The Configuration properties with which you can customise the auto-configuration
     * @property credentialsProvider CredentialsProvider-chain that retrieves the credentials
     */
    @Configuration
    open class BrokerAutoConfiguration(
        private val configuration: SlackBrokerConfigurationProperties,
        private val credentialsProvider: CredentialsProvider,
    ) : WebMvcConfigurer {

        /**
         * Registers the [InMemoryEventStore] when no other [EventStore] is registered
         */
        @ConditionalOnMissingBean
        @Bean
        open fun eventStore(): EventStore {
            return InMemoryEventStore()
        }

        /**
         * Registers the [EventBroker] which forwards events to all [EventReceiver]s
         */
        @Bean
        open fun eventBroker(
            slackEventReceivers: List<EventReceiver>,
            teamStore: TeamStore,
            eventStore: EventStore,
        ): EventBroker {
            return EventBroker(slackEventReceivers, teamStore, eventStore)
        }

        /**
         * Registers the [CommandBroker] which forwards commands to all [SlashCommandReceiver]s
         */
        @Bean
        open fun commandBroker(
            slackEventReceivers: List<SlashCommandReceiver>,
            teamStore: TeamStore,
            mismatchCommandReceiver: MismatchCommandReceiver?,
            slack: Slack,
        ): CommandBroker {
            return CommandBroker(slackEventReceivers, teamStore, slack, mismatchCommandReceiver)
        }


        /**
         * Adds argument-resolver to resolve incoming requests
         *
         * @param resolvers
         */
        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            val signingSecret = credentialsProvider.applicationCredentials().signingSecret

            resolvers.add(SlashCommandArgumentResolver(signingSecret))
            resolvers.add(EventArgumentResolver(signingSecret))
        }

        /**
         * Registers a logging receiver that logs all incoming requests
         * Can be turned off by setting [SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX].enabled to [false]
         * @return
         */
        @ConditionalOnProperty(
            prefix = SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX,
            name = ["enabled"],
            havingValue = "true",
            matchIfMissing = true
        )
        @Bean
        open fun sL4JLoggingReceiver(): SL4JLoggingReceiver {
            return SL4JLoggingReceiver()
        }

        /**
         * Registers a [MismatchCommandReceiver] that responds to unknown commands
         */
        @ConditionalOnMissingBean
        @ConditionalOnProperty(
            prefix = SlackBrokerConfigurationProperties.MISMATCH_PROPERTY_PREFIX,
            name = ["enabled"],
            havingValue = "true",
            matchIfMissing = true
        )
        @Bean
        open fun commandNotFoundMismatchReceiver(slackClient: Slack): MismatchCommandReceiver {
            return CommandNotFoundReceiver(configuration.commands.mismatch.text)
        }
    }

    /**
     * Auto-configuration that registers the [InstallationReceiver]s
     *
     * @property configuration The Configuration properties with which you can customise the auto-configuration
     */
    @Configuration
    open class InstallationAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties) {

        /**
         * Registers the InstallationBroker if error-redirect-url and success-redirect-url are set
         */
        @ConditionalOnProperty(
            prefix = SlackBrokerConfigurationProperties.Companion.INSTALLATION_PROPERTY_PREFIX,
            name = ["error-redirect-url", "success-redirect-url"]
        )
        @Bean
        open fun installationBroker(
            installationReceivers: List<InstallationReceiver>,
            teamStore: TeamStore,
            slackClient: Slack,
            credentialsProvider: CredentialsProvider,
        ): InstallationBroker {

            val installation = this.configuration.installation
            val applicationCredentials = credentialsProvider.applicationCredentials()

            return InstallationBroker(
                installationReceivers,
                teamStore,
                InstallationBroker.Config(
                    applicationCredentials.clientId,
                    applicationCredentials.clientSecret,
                    installation.successRedirectUrl,
                    installation.errorRedirectUrl
                ),
                slackClient,
            )
        }
    }

    /**
     * Registers a [SpringSlackClient] if no different client is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun slackClient(): Slack {
        return Slack.getInstance()
    }

    /**
     * Registers the [SlackExceptionHandler]
     */
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    open fun slackExceptionHandler(): SlackExceptionHandler {
        return SlackExceptionHandler(configuration.application.errorResponse)
    }

    /**
     * Registers a default credentials-provider chain if no different one is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun slackCredentialsProvider(): CredentialsProvider {
        return DefaultCredentialsProviderChain()
    }

    /**
     * Registers the [EvaluationReport]
     */
    @Bean
    open fun slackEvaluationReport(): io.hndrs.slack.broker.autoconfiguration.EvaluationReport {
        return io.hndrs.slack.broker.autoconfiguration.EvaluationReport()
    }
}
