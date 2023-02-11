package io.hndrs.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties
data class BaseConfigurationProperties(
    val logging: Logging = Logging(),
) {
    data class Logging(
        val enabled: Boolean = true,
    )
}
