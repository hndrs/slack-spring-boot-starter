package com.kreait.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties with which you can customise the auto-configuration
 */
@ConfigurationProperties(prefix = SlackBrokerConfigurationProperties.PROPERTY_PREFIX)
open class SlackBrokerConfigurationProperties {


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

    /**
    Group that contains installation feature related configurations
     */


    var installation: Installation = Installation()

    var logging: Logging = Logging()

    var application: Application = Application()

    var commands: Commands = Commands()

    var store: Store = Store()

    /**
     * Installation-properties containing the redirect-urls after an installation
     */
    open class Installation {

        /**
        redirect url that is used when an installation is successful
         */
        lateinit var successRedirectUrl: String

        /**
        redirect url that is used when there is an error during the installation
         */
        lateinit var errorRedirectUrl: String

    }

    /**
     * Application properties
     */
    open class Application {

        /**
         * Contains the response which is sent when an error occurs
         */
        var errorResponse: String = "Sorry i am having troubles right now"
    }

    /**
     * Logging properties which are used to customise the logging receiver
     */
    open class Logging {

        /**
        Enables Logging receiver [com.kreait.slack.broker.receiver.SL4JLoggingReceiver]
         */
        lateinit var enabled: String
    }

    /**
     * Command properties which are used to customise command-behaviour
     */
    open class Commands {

        var mismatch: Mismatch = Mismatch()

        /**
         * MismatchReceiver that responds with a default error message when no Command was found
         *
         */
        open class Mismatch {

            /**
            Enables Logging receiver [com.kreait.slack.broker.receiver.CommandNotFoundReceiver]
             */
            lateinit var enabled: String

            var text: String = "I am sorry i was not able to understand this"
        }
    }

    /**
     * Store properties which are used to customise the stores
     */
    open class Store {
        var team = Team()
        var user = User()

        /**
         * Team-store property
         */
        open class Team {
            lateinit var type: Type
        }

        /**
         * User-store property
         */
        open class User {
            lateinit var type: Type
        }


        /**
         * StorageType of a Store
         */
        enum class Type {
            MEMORY, FILE
        }

    }


}
