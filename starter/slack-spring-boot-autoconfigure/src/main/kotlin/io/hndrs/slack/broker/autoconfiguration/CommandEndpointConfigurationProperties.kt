package io.hndrs.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = CommandEndpointConfigurationProperties.PREFIX)
data class CommandEndpointConfigurationProperties(
    val unknownCommand: UnknownCommand = UnknownCommand(),
) {

    data class UnknownCommand(
        /**
         * enables unknown command default response
         */
        val enabled: Boolean = true,


        /**
         * default response string for unknown commands
         */
        val unknownCommandResponse: String = "I am sorry i was not able to understand this",
    )

    companion object {
        const val PREFIX = "slack.command-endpoint"
        const val UNKNOWN_COMMAND_PREFIX = "$PREFIX.unknown-command"
    }
}
