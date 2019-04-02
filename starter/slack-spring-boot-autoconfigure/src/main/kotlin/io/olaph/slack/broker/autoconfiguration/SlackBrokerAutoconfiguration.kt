package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InstallationBroker
import io.olaph.slack.broker.broker.InteractiveComponentBroker
import io.olaph.slack.broker.configuration.InteractiveResponseArgumentResolver
import io.olaph.slack.broker.configuration.SlackCommandArgumentResolver
import io.olaph.slack.broker.exception.SlackExceptionHandler
import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.security.VerificationHandlerInterceptor
import io.olaph.slack.broker.store.InMemoryTeamStore
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.spring.DefaultSlackClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class SlackBrokerAutoConfiguration {

    @Configuration
    open class BrokerConfiguration : WebMvcConfigurer {

        @ConditionalOnMissingBean
        @Bean
        open fun teamStore(): TeamStore {
            return InMemoryTeamStore()
        }

        @Bean
        open fun eventReceiver(slackEventReceivers: List<EventReceiver>, teamStore: TeamStore): EventBroker {
            return EventBroker(slackEventReceivers, teamStore)
        }

        @Bean
        open fun commandReceiver(slackEventReceivers: List<SlashCommandReceiver>, teamStore: TeamStore): CommandBroker {
            return CommandBroker(slackEventReceivers, teamStore)
        }

        @Bean
        open fun componentReceiver(slackEventReceivers: List<InteractiveComponentReceiver>, teamStore: TeamStore): InteractiveComponentBroker {
            return InteractiveComponentBroker(slackEventReceivers, teamStore)
        }

        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            resolvers.add(SlackCommandArgumentResolver())
            resolvers.add(InteractiveResponseArgumentResolver())
        }

        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX, name = ["enabled"], havingValue = "true", matchIfMissing = true)
        @Bean
        open fun sL4JLoggingReceiver(): SL4JLoggingReceiver {
            return SL4JLoggingReceiver()
        }
    }

    @Configuration
    open class InstallationConfiguration(private val configuration: SlackBrokerConfigurationProperties) {

        @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.INSTALLATION_PROPERTY_PREFIX,
                name = ["error-redirect-url", "success-redirect-url", "clientId", "clientSecret"])
        @Bean
        open fun installationBroker(installationReceivers: List<InstallationReceiver>,
                                    teamStore: TeamStore,
                                    slackClient: SlackClient): InstallationBroker {

            val installation = this.configuration.installation
            return InstallationBroker(installation.successRedirectUrl,
                    installation.errorRedirectUrl,
                    installationReceivers,
                    teamStore,
                    slackClient,
                    InstallationBroker.Config(installation.clientId, installation.clientSecret)
            )
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

    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.SECURITY_PROPERTY_PREFIX, name = ["signing-secret"])
    @Configuration
    open class SecurityConfiguration(private val configuration: SlackBrokerConfigurationProperties) : WebMvcConfigurer {

        override fun addInterceptors(registry: InterceptorRegistry) {
            registry.addInterceptor(VerificationHandlerInterceptor(configuration.security.signingSecret))
        }
    }

}
