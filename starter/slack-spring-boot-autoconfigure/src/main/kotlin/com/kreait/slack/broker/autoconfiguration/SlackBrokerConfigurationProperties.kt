package com.kreait.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = SlackBrokerConfigurationProperties.PROPERTY_PREFIX)
open class SlackBrokerConfigurationProperties {


    companion object {
        const val PROPERTY_PREFIX = "slack"
        const val INSTALLATION_PROPERTY_PREFIX = "$PROPERTY_PREFIX.installation"
        const val LOGGING_PROPERTY_PREFIX = "$PROPERTY_PREFIX.logging"
        const val COMMANDS_PROPERTY_PREFIX = "$PROPERTY_PREFIX.commands"
        const val MISMATCH_PROPERTY_PREFIX = "$COMMANDS_PROPERTY_PREFIX.mismatch"
        const val TEAM_STORE = "$PROPERTY_PREFIX.store"
    }

    /**
    Group that contains installation feature related configurations
     */


    var installation: Installation = Installation()

    var logging: Logging = Logging()

    var application: Application = Application()

    var commands: Commands = Commands()

    var store: Store = Store()


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

    open class Application {

        /**
        Enables Logging receiver [com.kreait.slack.broker.receiver.SL4JLoggingReceiver]
         */
        var errorResponse: String = "Sorry i am having troubles right now"
    }

    open class Logging {

        /**
        Enables Logging receiver [com.kreait.slack.broker.receiver.SL4JLoggingReceiver]
         */
        lateinit var enabled: String
    }

    open class Commands {

        var mismatch: Mismatch = Mismatch()

        open class Mismatch {

            /**
            Enables Logging receiver [com.kreait.slack.broker.receiver.CommandNotFoundReceiver]
             */
            lateinit var enabled: String

            var text: String = "I am sorry i was not able to understand this"
        }
    }

    open class Store {

        lateinit var type: Type

        enum class Type {
            MEMORY, FILE
        }

    }
}
