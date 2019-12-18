package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.api.SlackClient
import com.kreait.slack.broker.autoconfiguration.credentials.CredentialsProvider
import com.kreait.slack.broker.broker.InstallationBroker
import com.kreait.slack.broker.metrics.EventMetrics
import com.kreait.slack.broker.metrics.InstallationMetricsCollector
import com.kreait.slack.broker.receiver.InstallationReceiver
import com.kreait.slack.broker.store.team.TeamStore
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@AutoConfigureAfter(SlackBrokerAutoConfiguration::class, TeamStoreAutoconfiguration::class)
@Configuration
open class InstallationAutoConfiguration() : WebMvcConfigurer {


    @EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
    @Configuration
    open class Broker(private val prop: SlackBrokerConfigurationProperties) {

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

            val installation = this.prop.installation
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

    @AutoConfigureBefore(Broker::class)
    @ConditionalOnClass(MeterRegistry::class)
    @Configuration
    open class Metrics {

        /**
         * MeterBinder that tracks event metrics
         */
        @ConditionalOnMissingBean
        @Bean
        open fun eventMetrics(): EventMetrics {
            return EventMetrics()
        }
    }
}
