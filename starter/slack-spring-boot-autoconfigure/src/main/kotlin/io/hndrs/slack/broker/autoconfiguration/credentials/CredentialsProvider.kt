package io.hndrs.slack.broker.autoconfiguration.credentials

/**
 * Provider interface for slack credentials
 */
interface CredentialsProvider {

    /**
     * Holds credentials for the Application
     *
     * @return [ApplicationCredentials]
     */
    @Throws(ApplicationCredentialsException::class)
    fun applicationCredentials(): ApplicationCredentials
}

/**
 * Checks if registered [CredentialsProvider]s contain the credentials and returns them
 * @property credentialsProviders a list of [CredentialsProvider]s that will be checked
 */
@SuppressWarnings("detekt:TooGenericExceptionCaught")
abstract class CredentialsProviderChain(private val credentialsProviders: List<CredentialsProvider>) :
    CredentialsProvider {

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

/**
 * Data class that holds the slack-credentials
 */
data class ApplicationCredentials(val clientId: String, val clientSecret: String, val signingSecret: String)

/**
 * Exception that is thrown when an error occurs while retrieving the credentials
 */
class ApplicationCredentialsException(message: String) : RuntimeException(message)
