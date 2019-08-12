package com.kreait.slack.broker.autoconfiguration.credentials

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("CredentialsProviderChain")
class CredentialsProviderChainTests {


    @Test
    @DisplayName("Empty Chain")
    fun emptyProviderChain() {
        Assertions.assertThrows(ApplicationCredentialsException::class.java) { TestProviderChain(listOf()).applicationCredentials() }
    }

    @Test
    @DisplayName("Order Adhered")
    fun orderAdhered() {

        val one = ApplicationCredentials("clientId1", "clientSecret1", "signingSecret1")
        val two = ApplicationCredentials("clientId2", "clientSecret2", "signingSecret2")
        Assertions.assertEquals(one, TestProviderChain(listOf(TestCredentialsProvider(one), TestCredentialsProvider(two))).applicationCredentials())

        Assertions.assertEquals(two, TestProviderChain(listOf(TestCredentialsProvider(two), TestCredentialsProvider(one))).applicationCredentials())
    }

    @Test
    @DisplayName("Exception On Provider")
    fun exceptionOnProvider() {

        val two = ApplicationCredentials("clientId2", "clientSecret2", "signingSecret2")
        Assertions.assertEquals(two, TestProviderChain(listOf(TestCredentialsProvider(null), TestCredentialsProvider(two))).applicationCredentials())
    }

    class TestProviderChain(credentialsProviders: List<CredentialsProvider>) : CredentialsProviderChain(credentialsProviders)

    class TestCredentialsProvider(private val appCredentials: ApplicationCredentials?) : CredentialsProvider {


        override fun applicationCredentials(): ApplicationCredentials {
            return this.appCredentials ?: throw ApplicationCredentialsException("Application Credentials not set")
        }
    }
}
