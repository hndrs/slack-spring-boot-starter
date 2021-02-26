package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.group.auth.AuthGroup
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.dialog.DialogMethodGroup
import io.hndrs.slack.api.group.oauth.OauthMethodGroup
import io.hndrs.slack.api.group.pins.PinsMethodGroup
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.group.respond.RespondMethodGroup
import io.hndrs.slack.api.group.team.TeamMethodGroup
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.SpringSlackClient
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
                Assertions.assertDoesNotThrow { it.getBean(io.hndrs.slack.api.SlackClient::class.java) }
                Assertions.assertTrue(it.getBean(io.hndrs.slack.api.SlackClient::class.java) is io.hndrs.slack.api.spring.SpringSlackClient)
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
                Assertions.assertDoesNotThrow { it.getBean(io.hndrs.slack.api.SlackClient::class.java) }
                Assertions.assertTrue(it.getBean(io.hndrs.slack.api.SlackClient::class.java) is TestSlackClient)
            }
    }


    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testSlackClient(): io.hndrs.slack.api.SlackClient {
            return TestSlackClient()
        }
    }

    class TestSlackClient : io.hndrs.slack.api.SlackClient {
        override fun pins(): io.hndrs.slack.api.group.pins.PinsMethodGroup = throw  UnsupportedOperationException()

        override fun reminders(): io.hndrs.slack.api.group.reminders.RemindersMethodGroup = throw UnsupportedOperationException()

        override fun auth(): io.hndrs.slack.api.group.auth.AuthGroup = throw UnsupportedOperationException()

        override fun chat(): io.hndrs.slack.api.group.chat.ChatMethodGroup = throw UnsupportedOperationException()

        override fun dialog(): io.hndrs.slack.api.group.dialog.DialogMethodGroup = throw UnsupportedOperationException()

        override fun conversation(): io.hndrs.slack.api.group.conversations.ConversationsMethodGroup = throw UnsupportedOperationException()

        override fun users(): io.hndrs.slack.api.group.users.UsersMethodGroup = throw UnsupportedOperationException()

        override fun oauth(): io.hndrs.slack.api.group.oauth.OauthMethodGroup = throw UnsupportedOperationException()

        override fun respond(): io.hndrs.slack.api.group.respond.RespondMethodGroup = throw UnsupportedOperationException()

        override fun team(): io.hndrs.slack.api.group.team.TeamMethodGroup = throw UnsupportedOperationException()

        override fun usergroups(): io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup = throw UnsupportedOperationException()
    }

}
