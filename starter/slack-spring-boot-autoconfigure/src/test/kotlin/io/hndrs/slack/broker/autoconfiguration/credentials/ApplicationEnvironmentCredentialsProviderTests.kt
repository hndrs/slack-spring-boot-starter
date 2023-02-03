package io.hndrs.slack.broker.autoconfiguration.credentials

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.mock.env.MockEnvironment

@DisplayName("SystemPropertyCredentialsProvider")
class ApplicationEnvironmentCredentialsProviderTests {

    @Test
    @DisplayName("Valid Properties")
    fun validProperties() {
        // setup

        val clientId = "sampleClientId"
        val clientSecret = "sampleClientSecret"
        val signingSecret = "sampleSigningSecret"

        val environment = MockEnvironment()
            .withProperty("slack.application.client-id", clientId)
            .withProperty("slack.application.client-secret", clientSecret)
            .withProperty("slack.application.signing-secret", signingSecret)

        // test
        val applicationCredentials = ApplicationEnvironmentCredentialsProvider(environment)
            .applicationCredentials()

        // assert
        Assertions.assertEquals(clientId, applicationCredentials.clientId)
        Assertions.assertEquals(clientSecret, applicationCredentials.clientSecret)
        Assertions.assertEquals(signingSecret, applicationCredentials.signingSecret)
    }

    @Test
    @DisplayName("Missing Client Id")
    fun missingClientId() {
        // setup

        val clientSecret = "sampleClientSecret"
        val signingSecret = "sampleSigningSecret"
        val environment = MockEnvironment()
            .withProperty("slack.application.client-secret", clientSecret)
            .withProperty("slack.application.signing-secret", signingSecret)

        // test
        Assertions.assertThrows(ApplicationCredentialsException::class.java) {
            ApplicationEnvironmentCredentialsProvider(environment)
                .applicationCredentials()
        }
    }

    @Test
    @DisplayName("Missing Client Secret")
    fun missingClientSecret() {
        // setup

        val clientId = "sampleClientId"
        val signingSecret = "sampleSigningSecret"
        val environment = MockEnvironment()
            .withProperty("slack.application.client-id", clientId)
            .withProperty("slack.application.signing-secret", signingSecret)

        // test
        Assertions.assertThrows(ApplicationCredentialsException::class.java) {
            ApplicationEnvironmentCredentialsProvider(environment)
                .applicationCredentials()
        }
    }

    @Test
    @DisplayName("Missing Signing Secret")
    fun missingSigningSecret() {
        // setup

        val clientId = "sampleClientId"
        val clientSecret = "sampleClientSecret"
        val environment = MockEnvironment()
            .withProperty("slack.application.client-id", clientId)
            .withProperty("slack.application.client-secret", clientSecret)

        // test
        val applicationCredentials =

            // test
            Assertions.assertThrows(ApplicationCredentialsException::class.java) {
                ApplicationEnvironmentCredentialsProvider(environment)
                    .applicationCredentials()
            }
    }
}
