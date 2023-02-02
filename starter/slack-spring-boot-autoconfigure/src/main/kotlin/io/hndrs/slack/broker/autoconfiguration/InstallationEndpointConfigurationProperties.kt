package io.hndrs.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = InstallationEndpointConfigurationProperties.PREFIX)
data class InstallationEndpointConfigurationProperties(
    /**
    redirect url that is used when an installation is successful
     */
    val successRedirectUrl: String?,

    /**
    redirect url that is used when there is an error during the installation
     */
    val errorRedirectUrl: String?,
) {

    companion object {
        const val PREFIX = "slack.installation-endpoint"
    }
}

