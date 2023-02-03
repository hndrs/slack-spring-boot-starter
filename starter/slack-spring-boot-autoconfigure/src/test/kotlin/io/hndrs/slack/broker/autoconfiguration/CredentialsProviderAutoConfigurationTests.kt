package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.autoconfiguration.credentials.ApplicationCredentials
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.autoconfiguration.credentials.DefaultCredentialsProviderChain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class CredentialsProviderAutoConfigurationTests {

    @DisplayName("DefaultCredentialsProvider Registration")
    @Test
    fun slackCredentialsProviderRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                )
            )
            .run {
                Assertions.assertDoesNotThrow { it.getBean(CredentialsProvider::class.java) }
                Assertions.assertTrue(it.getBean(CredentialsProvider::class.java) is DefaultCredentialsProviderChain)
            }
    }

    @DisplayName("Custom SlackCredentialsProvider Registration")
    @Test
    fun slackCustomCredentialsProviderRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                )
            )
            .withUserConfiguration(TestCredentialsProviderConfiguration::class.java)
            .run {
                Assertions.assertDoesNotThrow { it.getBean(CredentialsProvider::class.java) }
                Assertions.assertTrue(it.getBean(CredentialsProvider::class.java) is TestCredentialsProvider)
            }
    }

    @Configuration
    open class TestCredentialsProviderConfiguration {

        @Bean
        open fun testCredentialsProvider(): CredentialsProvider {
            return TestCredentialsProvider()
        }
    }

    class TestCredentialsProvider : CredentialsProvider {
        override fun applicationCredentials(): ApplicationCredentials = ApplicationCredentials("", "", "")
    }
}
