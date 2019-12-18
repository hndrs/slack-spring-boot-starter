package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.BlockActions
import com.kreait.slack.api.contract.jackson.InteractiveMessage
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.spring.SpringSlackClient
import com.kreait.slack.broker.autoconfiguration.credentials.CredentialsProvider
import com.kreait.slack.broker.autoconfiguration.credentials.DefaultCredentialsProviderChain
import com.kreait.slack.broker.broker.CommandBroker
import com.kreait.slack.broker.broker.EventBroker
import com.kreait.slack.broker.broker.InstallationBroker
import com.kreait.slack.broker.broker.InteractiveComponentBroker
import com.kreait.slack.broker.configuration.EventArgumentResolver
import com.kreait.slack.broker.configuration.InteractiveResponseArgumentResolver
import com.kreait.slack.broker.configuration.SlackCommandArgumentResolver
import com.kreait.slack.broker.exception.SlackExceptionHandler
import com.kreait.slack.broker.metrics.CommandMetrics
import com.kreait.slack.broker.metrics.CommandMetricsCollector
import com.kreait.slack.broker.metrics.EventMetrics
import com.kreait.slack.broker.metrics.EventMetricsCollector
import com.kreait.slack.broker.metrics.InstallationMetrics
import com.kreait.slack.broker.metrics.InstallationMetricsCollector
import com.kreait.slack.broker.metrics.InteractiveComponentMetrics
import com.kreait.slack.broker.metrics.InteractiveComponentMetricsCollector
import com.kreait.slack.broker.receiver.CommandNotFoundReceiver
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.receiver.InstallationReceiver
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.receiver.MismatchCommandReceiver
import com.kreait.slack.broker.receiver.SL4JLoggingReceiver
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.event.EventStore
import com.kreait.slack.broker.store.event.InMemoryEventStore
import com.kreait.slack.broker.store.team.TeamStore
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
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
    open class BrokerAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties, private val credentialsProvider: CredentialsProvider) : WebMvcConfigurer {

        /**
         * Registers the [CommandBroker] which forwards commands to all [SlashCommandReceiver]s
         */
        @Bean
        open fun commandBroker(slackEventReceivers: List<SlashCommandReceiver>, teamStore: TeamStore, mismatchCommandReceiver: MismatchCommandReceiver?, metricsCollector: CommandMetricsCollector?): CommandBroker {
            return CommandBroker(slackEventReceivers, teamStore, mismatchCommandReceiver, metricsCollector)
        }

        /**
         * Registers the [InteractiveComponentBroker] which forwards interactive-components to all [InteractiveComponentReceiver]s
         */
        @Bean
        open fun componentBroker(slackInteractiveMessageReceivers: List<InteractiveComponentReceiver<InteractiveMessage>>, slackBlockActionReceivers: List<InteractiveComponentReceiver<BlockActions>>, teamStore: TeamStore, metricsCollector: InteractiveComponentMetricsCollector?): InteractiveComponentBroker {
            return InteractiveComponentBroker(slackBlockActionReceivers, slackInteractiveMessageReceivers, teamStore, metricsCollector)
        }

        /**
         * Adds argument-resolver to resolve incoming requests
         *
         * @param resolvers
         */
        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            val signingSecret = credentialsProvider.applicationCredentials().signingSecret

            resolvers.add(SlackCommandArgumentResolver(signingSecret))
            resolvers.add(InteractiveResponseArgumentResolver(signingSecret))
            resolvers.add(EventArgumentResolver(signingSecret))
        }

        /**
         * Registers a logging receiver that logs all incoming requests
         * Can be turned off by setting [SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX].enabled to [false]
         * @return
         */
        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true", matchIfMissing = true)
        @Bean
        open fun sL4JLoggingReceiver(): SL4JLoggingReceiver {
            return SL4JLoggingReceiver()
        }

        /**
         * Registers a [MismatchCommandReceiver] that responds to unknown commands
         */
        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.MISMATCH_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true", matchIfMissing = true)
        @Bean
        open fun commandNotFoundMismatchReceiver(slackClient: SlackClient): MismatchCommandReceiver {
            return CommandNotFoundReceiver(slackClient, configuration.commands.mismatch.text)
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
        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.Companion.INSTALLATION_PROPERTY_PREFIX,
                name = ["error-redirect-url", "success-redirect-url"])
        @Bean
        open fun installationBroker(installationReceivers: List<InstallationReceiver>,
                                    teamStore: TeamStore,
                                    slackClient: SlackClient,
                                    credentialsProvider: CredentialsProvider,
                                    metricsCollector: InstallationMetricsCollector?): InstallationBroker {

            val installation = this.configuration.installation
            val applicationCredentials = credentialsProvider.applicationCredentials()

            return InstallationBroker(
                    installationReceivers,
                    metricsCollector,
                    teamStore,
                    slackClient,
                    InstallationBroker.Config(applicationCredentials.clientId, applicationCredentials.clientSecret, installation.successRedirectUrl, installation.errorRedirectUrl)
            )
        }
    }

    /**
     * Auto-configuration that registers the MeterBinders
     */
    @AutoConfigureBefore(InstallationAutoConfiguration::class, UserStoreAutoConfiguration::class, TeamStoreAutoconfiguration::class, BrokerAutoConfiguration::class)
    @ConditionalOnClass(MeterRegistry::class)
    @Configuration
    open class SlackBrokerMetricsAutoConfiguration {

        /**
         * MeterBinder that tracks installation metrics
         */
        @ConditionalOnMissingBean
        @Bean
        open fun installationMetrics(): InstallationMetrics {
            return InstallationMetrics()
        }



        /**
         * MeterBinder that tracks command metrics
         */
        @ConditionalOnMissingBean
        @Bean
        open fun commandMetrics(): CommandMetrics {
            return CommandMetrics()
        }

        /**
         * MeterBinder that tracks interactive-component metrics
         */
        @ConditionalOnMissingBean
        @Bean
        open fun interactiveComponentMetrics(): InteractiveComponentMetrics {
            return InteractiveComponentMetrics()
        }
    }


    /**
     * Registers a [SpringSlackClient] if no different client is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun slackClient(): SlackClient {
        return SpringSlackClient()
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
    open fun slackEvaluationReport(): EvaluationReport {
        return EvaluationReport()
    }
}
