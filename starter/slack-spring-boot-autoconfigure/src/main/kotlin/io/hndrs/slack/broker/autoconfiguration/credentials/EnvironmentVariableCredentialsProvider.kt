package io.hndrs.slack.broker.autoconfiguration.credentials

/**
 * [CredentialsProvider] that fetches the Credentials from the environment variables
 */
class EnvironmentVariableCredentialsProvider : CredentialsProvider {

    override fun applicationCredentials(): ApplicationCredentials {
        val clientId = System.getenv(SLACK_APP_CLIENT_ID_VAR)
        val clientSecret = System.getenv(SLACK_APP_CLIENT_SECRET_VAR)
        val signingSecret = System.getenv(SLACK_APP_SIGNING_SECRET_VAR)

        if (clientId != null && clientSecret != null && signingSecret != null) {
            return ApplicationCredentials(clientId, clientSecret, signingSecret)
        }
        throw ApplicationCredentialsException(
            "Unable to load credentials: not all environment variables have been set"
        )
    }

    companion object {
        private const val SLACK_APP_CLIENT_ID_VAR = "SLACK_APP_CLIENT_ID"
        private const val SLACK_APP_CLIENT_SECRET_VAR = "SLACK_APP_CLIENT_SECRET"
        private const val SLACK_APP_SIGNING_SECRET_VAR = "SLACK_APP_SIGNING_SECRET"
    }
}
