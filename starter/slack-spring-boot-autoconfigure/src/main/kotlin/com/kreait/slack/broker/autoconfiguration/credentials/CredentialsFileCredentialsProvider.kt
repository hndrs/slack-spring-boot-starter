package com.kreait.slack.broker.autoconfiguration.credentials

import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * [CredentialsProvider] that fetches the Credentials from a file
 */
class CredentialsFileCredentialsProvider : CredentialsProvider {

    companion object {

        private const val CREDENTIALS_PROFILE_FILE_NAME = "credentials"
        const val SLACK_APP_CLIENT_ID_VAR = "slack_app_client_id"
        const val SLACK_APP_CLIENT_SECRET_VAR = "slack_app_client_secret"
        const val SLACK_APP_SIGNING_SECRET_VAR = "slack_app_signing_secret"


        private fun profileDirectory(): File = File(homeDirectory(), ".slack")

        private fun homeDirectory(): String = System.getProperty("user.home")
            ?: throw ApplicationCredentialsException("Unable to load credentials:'user.home' System property is not set.")

    }

    override fun applicationCredentials(): ApplicationCredentials {
        val credentialsFile = File(profileDirectory(), CREDENTIALS_PROFILE_FILE_NAME)
        if (credentialsFile.exists() && credentialsFile.isFile) {
            val credentialsProperties = Properties()
            credentialsProperties.load(FileInputStream(credentialsFile))

            val clientId = credentialsProperties[SLACK_APP_CLIENT_ID_VAR] as? String
            val clientSecret = credentialsProperties[SLACK_APP_CLIENT_SECRET_VAR] as? String
            val signingSecret = credentialsProperties[SLACK_APP_SIGNING_SECRET_VAR] as? String

            if (clientId != null && clientSecret != null && signingSecret != null) {
                return ApplicationCredentials(clientId, clientSecret, signingSecret)
            }
            throw ApplicationCredentialsException("Unable to load credentials:'~/.slack/credentials' not all values are set")
        }
        throw ApplicationCredentialsException("Unable to load credentials:'~/.slack/credentials' file is not set.")
    }
}
