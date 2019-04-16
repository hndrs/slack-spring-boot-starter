package io.olaph.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = SlackBrokerConfigurationProperties.PROPERTY_PREFIX)
open class SlackBrokerConfigurationProperties {

    companion object {
        const val PROPERTY_PREFIX = "slack"
        const val INSTALLATION_PROPERTY_PREFIX = "$PROPERTY_PREFIX.installation"
        const val LOGGING_PROPERTY_PREFIX = "$PROPERTY_PREFIX.logging"
        const val SECURITY_PROPERTY_PREFIX = "$PROPERTY_PREFIX.security"
        const val COMMANDS_PROPERTY_PREFIX = "$PROPERTY_PREFIX.commands"
        const val MISMATCH_PROPERTY_PREFIX = "$COMMANDS_PROPERTY_PREFIX.mismatch"
    }

    /**
    Group that contains installation feature related configurations
     */
    var installation: Installation = Installation()

    /**
    Group that contains security feature related configurations
     */
    var security: Security = Security()

    var logging: Logging = Logging()

    var commands: Commands = Commands()


    open class Installation {

        /**
        redirect url that is used when an installation is successful
         */
        lateinit var successRedirectUrl: String

        /**
        redirect url that is used when there is an error during the installation
         */
        lateinit var errorRedirectUrl: String

        /**
        client id of your slack app
         */
        lateinit var clientId: String

        /**
        client secret of your slack app
         */
        lateinit var clientSecret: String

    }


    open class Security {

        /**
        Secret that is used to verify signed requests from slack [Slack Doc](https://api.slack.com/docs/verifying-requests-from-slack)
         */
        var signingSecret: String? = null
    }

    open class Logging {

        /**
        Enables Logging receiver [io.olaph.slack.broker.receiver.SL4JLoggingReceiver]
         */
        lateinit var enabled: String
    }

    open class Commands {

        var mismatch: Mismatch = Mismatch()

        open class Mismatch {

            /**
            Enables Logging receiver [io.olaph.slack.broker.receiver.CommandNotFoundReceiver]
             */
            lateinit var enabled: String

            var text: String = "I am sorry i was not able to understand this"
        }


    }
}
