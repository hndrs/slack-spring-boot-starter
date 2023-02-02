package io.hndrs.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


/**
 * Configuration properties with which you can customise the auto-configuration
 */
@ConfigurationProperties(prefix = SlackBrokerConfigurationProperties.PROPERTY_PREFIX)
data class SlackBrokerConfigurationProperties @ConstructorBinding constructor(
    /**
    Group that contains installation feature related configurations
     */
    val installation: Installation,
    val logging: Logging = Logging(),
    val application: Application = Application(),
    val commands: Commands = Commands(),
    val store: Store = Store(),
) {
    data class Installation(

        /**
        redirect url that is used when an installation is successful
         */
        val successRedirectUrl: String,

        /**
        redirect url that is used when there is an error during the installation
         */
        val errorRedirectUrl: String,
    )

    data class Logging(
        /**
        Enables Logging receiver [io.hndrs.slack.broker.receiver.SL4JLoggingReceiver]
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

    data class Store(
        val team: Team = Team(),
        val user: User = User(),
    ) {
        data class Team(
            val type: Type = Type.MEMORY,
        )

        data class User(
            val type: Type = Type.MEMORY,
        )

        enum class Type {
            MEMORY, FILE
        }
    }

    companion object {
        const val PROPERTY_PREFIX = "slack"
        const val INSTALLATION_PROPERTY_PREFIX = "$PROPERTY_PREFIX.installation"
        const val LOGGING_PROPERTY_PREFIX = "$PROPERTY_PREFIX.logging"
        const val COMMANDS_PROPERTY_PREFIX = "$PROPERTY_PREFIX.commands"
        const val MISMATCH_PROPERTY_PREFIX = "$COMMANDS_PROPERTY_PREFIX.mismatch"
        const val STORE_PREFIX = "$PROPERTY_PREFIX.store"
        const val TEAM_STORE = "$STORE_PREFIX.team"
        const val USER_STORE = "$STORE_PREFIX.user"
    }
}
