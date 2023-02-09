package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.installation.InstallationBroker
import io.hndrs.slack.broker.installation.InstallationReceiver
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(InstallationEndpointConfigurationProperties::class)
open class InstallationEndpointAutoConfiguration {

    /**
     * Registers the InstallationBroker if error-redirect-url and success-redirect-url are set
     */
    @ConditionalOnProperty(
        prefix = InstallationEndpointConfigurationProperties.PREFIX,
        name = ["error-redirect-url", "success-redirect-url"]
    )
    @Bean
    open fun installationBroker(
        installationReceivers: List<InstallationReceiver>,
        teamStore: TeamStore,
        slackClient: Slack,
        credentialsProvider: CredentialsProvider,
        properties: InstallationEndpointConfigurationProperties,
    ): InstallationBroker {
        val applicationCredentials = credentialsProvider.applicationCredentials()

        return InstallationBroker(
            installationReceivers,
            teamStore,
            InstallationBroker.Config(
                applicationCredentials.clientId,
                applicationCredentials.clientSecret,
                properties.successRedirectUrl!!,
                properties.errorRedirectUrl!!
            ),
            slackClient,
        )
    }
}
