package io.olaph.slack.broker.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "slack")
open class SlackBrokerConfigurationProperties {

    var installation: Installation = Installation()

    open class Installation {

        /*
         * Successful installation redirect url
         */
        lateinit var successRedirectUrl: String

        /*
         * Error during installation redirect url
         */
        lateinit var errorRedirectUrl: String
    }
}
