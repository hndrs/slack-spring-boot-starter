package com.kreait.slack.broker.autoconfiguration.credentials

import org.springframework.core.env.Environment
import org.springframework.core.env.get

/**
 * [CredentialsProvider] that fetches the Credentials from a spring application [Environment]
 */
class ApplicationEnvironmentCredentialsProvider(private val environment: Environment) : CredentialsProvider {

    companion object {
        private const val APPLICATION_PROPERTY_PREFIX = "slack.application"
    }

    override fun applicationCredentials(): ApplicationCredentials {

        val clientId = environment["$APPLICATION_PROPERTY_PREFIX.client-id"]?.trim()
        val clientSecret = environment["$APPLICATION_PROPERTY_PREFIX.client-secret"]?.trim()
        val signingSecret = environment["$APPLICATION_PROPERTY_PREFIX.signing-secret"]?.trim()

        if (clientId != null && clientSecret != null && signingSecret != null) {
            return ApplicationCredentials(clientId, clientSecret, signingSecret)
        }
        throw  ApplicationCredentialsException("Unable to load credentials: not all system properties have been set")

    }

}
