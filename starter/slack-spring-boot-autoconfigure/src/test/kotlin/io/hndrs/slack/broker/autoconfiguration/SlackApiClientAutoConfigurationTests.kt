package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class SlackApiClientAutoConfigurationTests {


    @DisplayName("DefaultSlackClient Registration")
    @Test
    fun slackClientRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    SlackBrokerAutoConfiguration::class.java,
                    TeamStoreAutoconfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .run {
                Assertions.assertDoesNotThrow { it.getBean(Slack::class.java) }
            }
    }

    @DisplayName("Custom SlackClient Registration")
    @Test
    fun customSlackClientRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    SlackBrokerAutoConfiguration::class.java,
                    TeamStoreAutoconfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .withUserConfiguration(TestConfiguration::class.java)
            .run {
                Assertions.assertDoesNotThrow { it.getBean(Slack::class.java) }
                Assertions.assertTrue(it.getBean(Slack::class.java) is TestSlack)
            }
    }


    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testSlackClient(): Slack {
            return TestSlack()
        }
    }

    class TestSlack : Slack()

}
