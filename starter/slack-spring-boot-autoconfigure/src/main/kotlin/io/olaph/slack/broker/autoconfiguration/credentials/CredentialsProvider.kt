package io.olaph.slack.broker.autoconfiguration.credentials

import java.io.File
import java.io.FileInputStream
import java.util.Properties


interface CredentialsProvider {

    fun applicationCredentials(): ApplicationCredentials

}

class DefaultCredentialsProviderChain
    : CredentialsProviderChain(listOf(CredentialsFileCredentialsProvider(), SystemPropertyCredentialsProvider(), EnvironmentVariableCredentialsProvider()))

abstract class CredentialsProviderChain(private val credentialsProviders: List<CredentialsProvider>) : CredentialsProvider {

    override fun applicationCredentials(): ApplicationCredentials {
        val exceptions = mutableListOf<String>()
        credentialsProviders.forEach { provider ->
            try {
                return provider.applicationCredentials()
            } catch (e: Exception) {
                e.message?.let { exceptions.add(it) }
            }
        }

        throw ApplicationCredentialsException(exceptions.joinToString(separator = "\n"))
    }
}


class SystemPropertyCredentialsProvider : CredentialsProvider {

    companion object {
        private const val APPLICATION_PROPERTY_PREFIX = "slack.application"
    }

    override fun applicationCredentials(): ApplicationCredentials {
        val clientId = System.getProperty("$APPLICATION_PROPERTY_PREFIX.client-id")?.trim()
        val clientSecret = System.getProperty("$APPLICATION_PROPERTY_PREFIX.client-secret")?.trim()
        val signingSecret = System.getProperty("$APPLICATION_PROPERTY_PREFIX.signing-secret")?.trim()

        if (clientId != null && clientSecret != null && signingSecret != null) {
            return ApplicationCredentials(clientId, clientSecret, signingSecret)
        }
        throw  ApplicationCredentialsException("Unable to load credentials: not all system properties have been set")
    }

}

class EnvironmentVariableCredentialsProvider : CredentialsProvider {

    companion object {
        private const val SLACK_APP_CLIENT_ID_VAR = "SLACK_APP_CLIENT_ID"
        private const val SLACK_APP_CLIENT_SECRET_VAR = "SLACK_APP_CLIENT_SECRET"
        private const val SLACK_APP_SIGNING_SECRET_VAR = "SLACK_APP_SIGNING_SECRET"

    }

    override fun applicationCredentials(): ApplicationCredentials {

        val clientId = System.getenv(SLACK_APP_CLIENT_ID_VAR)
        val clientSecret = System.getenv(SLACK_APP_CLIENT_SECRET_VAR)
        val signingSecret = System.getenv(SLACK_APP_SIGNING_SECRET_VAR)

        if (clientId != null && clientSecret != null && signingSecret != null) {
            return ApplicationCredentials(clientId, clientSecret, signingSecret)
        }
        throw  ApplicationCredentialsException("Unable to load credentials: not all environment variables have been set")

    }

}

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

data class ApplicationCredentials(val clientId: String, val clientSecret: String, val signingSecret: String)

class ApplicationCredentialsException(message: String) : RuntimeException(message)
