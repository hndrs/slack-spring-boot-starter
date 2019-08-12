package com.kreait.slack.broker.autoconfiguration.credentials

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("SystemPropertyCredentialsProvider")
class SystemPropertyCredentialsProviderTests {

    @Test
    @DisplayName("Valid Properties")
    @ExtendWith(CredentialsFileCredentialsProviderTests.DisabledOnExistingCredentialsFile::class)
    fun validProperties() {
        // setup

        val clientId = "sampleClientId"
        val clientSecret = "sampleClientSecret"
        val signingSecret = "sampleSigningSecret"

        System.setProperty("slack.application.client-id", clientId)
        System.setProperty("slack.application.client-secret", clientSecret)
        System.setProperty("slack.application.signing-secret", signingSecret)

        // test
        val applicationCredentials = SystemPropertyCredentialsProvider()
                .applicationCredentials()

        // assert
        Assertions.assertEquals(clientId, applicationCredentials.clientId)
        Assertions.assertEquals(clientSecret, applicationCredentials.clientSecret)
        Assertions.assertEquals(signingSecret, applicationCredentials.signingSecret)

        // cleanup
        clearTestProperties()
    }

    @Test
    @DisplayName("Missing Client Id")
    @ExtendWith(CredentialsFileCredentialsProviderTests.DisabledOnExistingCredentialsFile::class)
    fun missingClientId() {
        // setup

        val clientSecret = "sampleClientSecret"
        val signingSecret = "sampleSigningSecret"

        System.setProperty("slack.application.client-secret", clientSecret)
        System.setProperty("slack.application.signing-secret", signingSecret)

        // test
        Assertions.assertThrows(ApplicationCredentialsException::class.java) { SystemPropertyCredentialsProvider().applicationCredentials() }

        // cleanup
        clearTestProperties()
    }

    @Test
    @DisplayName("Missing Client Secret")
    @ExtendWith(CredentialsFileCredentialsProviderTests.DisabledOnExistingCredentialsFile::class)
    fun missingClientSecret() {
        // setup

        val clientId = "sampleClientId"
        val signingSecret = "sampleSigningSecret"

        System.setProperty("slack.application.client-id", clientId)
        System.setProperty("slack.application.signing-secret", signingSecret)

        // test
        Assertions.assertThrows(ApplicationCredentialsException::class.java) { SystemPropertyCredentialsProvider().applicationCredentials() }

        // cleanup
        clearTestProperties()
    }

    @Test
    @DisplayName("Missing Signing Secret")
    @ExtendWith(CredentialsFileCredentialsProviderTests.DisabledOnExistingCredentialsFile::class)
    fun missingSigningSecret() {
        // setup

        val clientId = "sampleClientId"
        val clientSecret = "sampleClientSecret"

        System.setProperty("slack.application.client-id", clientId)
        System.setProperty("slack.application.client-secret", clientSecret)

        // test
        Assertions.assertThrows(ApplicationCredentialsException::class.java) { SystemPropertyCredentialsProvider().applicationCredentials() }

        // cleanup
        clearTestProperties()
    }

    private fun clearTestProperties() {
        System.clearProperty("slack.application.client-id")
        System.clearProperty("slack.application.client-secret")
        System.clearProperty("slack.application.signing-secret")
    }
}
