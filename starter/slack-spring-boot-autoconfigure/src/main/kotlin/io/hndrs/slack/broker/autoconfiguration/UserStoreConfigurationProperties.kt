package io.hndrs.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = UserStoreConfigurationProperties.PREFIX)
data class UserStoreConfigurationProperties(
    val type: Type = Type.MEMORY,
) {
    enum class Type {
        MEMORY, FILE
    }

    companion object {
        const val PREFIX = "slack.store.user"
    }
}
