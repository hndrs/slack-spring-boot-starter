package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.installation.InstallationEndpoint
import io.hndrs.slack.broker.installation.InstallationHandler
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
        installationHandlers: List<InstallationHandler>,
        teamStore: TeamStore,
        slackClient: Slack,
        credentialsProvider: CredentialsProvider,
        properties: InstallationEndpointConfigurationProperties,
    ): InstallationEndpoint {
        val applicationCredentials = credentialsProvider.applicationCredentials()

        return InstallationEndpoint(
            installationHandlers,
            teamStore,
            InstallationEndpoint.Config(
                applicationCredentials.clientId,
                applicationCredentials.clientSecret,
                properties.successRedirectUrl!!,
                properties.errorRedirectUrl!!
            ),
            slackClient,
        )
    }
}
