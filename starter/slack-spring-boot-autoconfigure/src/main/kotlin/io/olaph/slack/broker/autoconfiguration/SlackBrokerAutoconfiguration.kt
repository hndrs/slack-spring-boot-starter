package io.olaph.slack.broker.autoconfiguration

import io.micrometer.core.instrument.MeterRegistry
import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InstallationBroker
import io.olaph.slack.broker.broker.InteractiveComponentBroker
import io.olaph.slack.broker.configuration.EventRequestArgumentResolver
import io.olaph.slack.broker.configuration.InteractiveResponseArgumentResolver
import io.olaph.slack.broker.configuration.SlackCommandArgumentResolver
import io.olaph.slack.broker.exception.SlackExceptionHandler
import io.olaph.slack.broker.metrics.CommandMetrics
import io.olaph.slack.broker.metrics.CommandMetricsCollector
import io.olaph.slack.broker.metrics.EventMetrics
import io.olaph.slack.broker.metrics.EventMetricsCollector
import io.olaph.slack.broker.metrics.InstallationMetrics
import io.olaph.slack.broker.metrics.InstallationMetricsCollector
import io.olaph.slack.broker.metrics.InteractiveComponentMetrics
import io.olaph.slack.broker.metrics.InteractiveComponentMetricsCollector
import io.olaph.slack.broker.receiver.CommandNotFoundReceiver
import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.MismatchCommandReciever
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.InMemoryTeamStore
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.spring.DefaultSlackClient
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

@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class SlackBrokerAutoConfiguration {

    @Configuration
    open class BrokerAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties) : WebMvcConfigurer {

        @ConditionalOnMissingBean
        @Bean
        open fun teamStore(): TeamStore {
            return InMemoryTeamStore()
        }

        @Bean
        open fun eventBroker(slackEventReceivers: List<EventReceiver>, teamStore: TeamStore, metricsCollector: EventMetricsCollector?): EventBroker {
            return EventBroker(slackEventReceivers, teamStore, metricsCollector)
        }

        @Bean
        open fun commandBroker(slackEventReceivers: List<SlashCommandReceiver>, teamStore: TeamStore, mismatchCommandReceiver: MismatchCommandReciever?, metricsCollector: CommandMetricsCollector?): CommandBroker {
            return CommandBroker(slackEventReceivers, teamStore, mismatchCommandReceiver, metricsCollector)
        }

        @Bean
        open fun componentBroker(slackEventReceivers: List<InteractiveComponentReceiver>, teamStore: TeamStore, metricsCollector: InteractiveComponentMetricsCollector?): InteractiveComponentBroker {
            return InteractiveComponentBroker(slackEventReceivers, teamStore, metricsCollector)
        }

        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            val signingSecret = this.configuration.security.signingSecret

            resolvers.add(SlackCommandArgumentResolver(signingSecret))
            resolvers.add(InteractiveResponseArgumentResolver(signingSecret))
            resolvers.add(EventRequestArgumentResolver(signingSecret))
        }

        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true", matchIfMissing = true)
        @Bean
        open fun sL4JLoggingReceiver(): SL4JLoggingReceiver {
            return SL4JLoggingReceiver()
        }

        @ConditionalOnMissingBean
        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.MISMATCH_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true", matchIfMissing = true)
        @Bean
        open fun commandNotFoundMismatchReceiver(slackClient: SlackClient): MismatchCommandReciever {
            return CommandNotFoundReceiver(slackClient, configuration.commands.mismatch.text)
        }
    }

    @Configuration
    open class InstallationAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties) {

        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.INSTALLATION_PROPERTY_PREFIX,
                name = ["error-redirect-url", "success-redirect-url", "client-id", "client-secret"])
        @Bean
        open fun installationBroker(installationReceivers: List<InstallationReceiver>,
                                    teamStore: TeamStore,
                                    slackClient: SlackClient,
                                    metricsCollector: InstallationMetricsCollector?): InstallationBroker {

            val installation = this.configuration.installation
            return InstallationBroker(
                    installationReceivers,
                    metricsCollector,
                    teamStore,
                    slackClient,
                    InstallationBroker.Config(installation.clientId, installation.clientSecret, installation.successRedirectUrl, installation.errorRedirectUrl)
            )
        }

    }

    @AutoConfigureBefore(InstallationAutoConfiguration::class, BrokerAutoConfiguration::class)
    @ConditionalOnClass(MeterRegistry::class)
    @Configuration
    open class SlackBrokerMetricsAutoConfiguration {

        @ConditionalOnMissingBean
        @Bean
        open fun installationMetrics(): InstallationMetrics {
            return InstallationMetrics()
        }

        @ConditionalOnMissingBean
        @Bean
        open fun eventMetrics(): EventMetrics {
            return EventMetrics()
        }

        @ConditionalOnMissingBean
        @Bean
        open fun commandMetrics(): CommandMetrics {
            return CommandMetrics()
        }

        @ConditionalOnMissingBean
        @Bean
        open fun interactiveComponentMetrics(): InteractiveComponentMetrics {
            return InteractiveComponentMetrics()
        }
    }

    @ConditionalOnMissingBean
    @Bean
    open fun slackClient(): SlackClient {
        return DefaultSlackClient()
    }

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    open fun slackExceptionHandler(): SlackExceptionHandler {
        return SlackExceptionHandler()
    }

}
