package io.hndrs.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties with which you can customise the auto-configuration
 */
@ConfigurationProperties(prefix = SlackBrokerConfigurationProperties.PROPERTY_PREFIX)
data class SlackBrokerConfigurationProperties(
    val logging: Logging = Logging(),
    val application: Application = Application(),
    val commands: Commands = Commands(),
) {

    data class Logging(
        /**
        Enables Logging receiver [io.hndrs.slack.broker.receiver.SL4JLoggingHandler]
         */
        val enabled: Boolean = true,
    )

    data class Application(
        /**
         * Contains the response which is sent when an error occurs
         */
        val errorResponse: String = "Sorry i am having troubles right now",
    )

    data class Commands(
        val mismatch: Mismatch = Mismatch(),
    ) {
        data class Mismatch(

            /**
            Enables Logging receiver [io.hndrs.slack.broker.receiver.CommandNotFoundReceiver]
             */
            val enabled: Boolean = false,

            val text: String = "I am sorry i was not able to understand this",
        )
    }

    companion object {
        const val PROPERTY_PREFIX = "slack"
        const val LOGGING_PROPERTY_PREFIX = "$PROPERTY_PREFIX.logging"
        const val COMMANDS_PROPERTY_PREFIX = "$PROPERTY_PREFIX.commands"
        const val MISMATCH_PROPERTY_PREFIX = "$COMMANDS_PROPERTY_PREFIX.mismatch"
    }
}
