package com.kreait.slack.broker.autoconfiguration.credentials

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CredentialsProviderTest {


    @Disabled
    @Test
    @DisplayName("EnvironmentVariableCredentialsProvider")
    fun envVarProvider() {
        assertFalse(true)
        //TODO write JUnit5 Extension
    }

    @Disabled
    @Test
    @DisplayName("DefaultCredentialsProviderChain")
    fun credentialsProviderChain() {
        assertFalse(true)
    }
}
