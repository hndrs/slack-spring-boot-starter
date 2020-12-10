package com.kreait.slack.broker.autoconfiguration.credentials

/**
 * [CredentialsProvider] that fetches the Credentials from the system properties
 */
class SystemPropertyCredentialsProvider : CredentialsProvider {

    override fun applicationCredentials(): ApplicationCredentials {
        val clientId = System.getProperty("$APPLICATION_PROPERTY_PREFIX.client-id")?.trim()
        val clientSecret = System.getProperty("$APPLICATION_PROPERTY_PREFIX.client-secret")?.trim()
        val signingSecret = System.getProperty("$APPLICATION_PROPERTY_PREFIX.signing-secret")?.trim()

        if (clientId != null && clientSecret != null && signingSecret != null) {
            return ApplicationCredentials(clientId, clientSecret, signingSecret)
        }
        throw  ApplicationCredentialsException("Unable to load credentials: not all system properties have been set")
    }

    companion object {
        private const val APPLICATION_PROPERTY_PREFIX = "slack.application"
    }
}
