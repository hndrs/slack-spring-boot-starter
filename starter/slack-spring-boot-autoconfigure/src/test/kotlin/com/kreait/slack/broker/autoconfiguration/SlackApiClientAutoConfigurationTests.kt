package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.dialog.DialogMethodGroup
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.group.respond.RespondMethodGroup
import com.kreait.slack.api.group.team.TeamMethodGroup
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.spring.SpringSlackClient
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
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(SlackClient::class.java) }
                    Assertions.assertTrue(it.getBean(SlackClient::class.java) is SpringSlackClient)
                }
    }

    @DisplayName("Custom SlackClient Registration")
    @Test
    fun customSlackClientRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(SlackClient::class.java) }
                    Assertions.assertTrue(it.getBean(SlackClient::class.java) is TestSlackClient)
                }
    }


    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testSlackClient(): SlackClient {
            return TestSlackClient()
        }
    }

    class TestSlackClient : SlackClient {
        override fun reminders(): RemindersMethodGroup = throw UnsupportedOperationException()

        override fun groups(): GroupsMethodGroup = throw UnsupportedOperationException()

        override fun auth(): AuthGroup = throw UnsupportedOperationException()

        override fun chat(): ChatMethodGroup = throw UnsupportedOperationException()

        override fun dialog(): DialogMethodGroup = throw UnsupportedOperationException()

        override fun conversation(): ConversationsMethodGroup = throw UnsupportedOperationException()

        override fun channel(): ChannelsMethodGroup = throw UnsupportedOperationException()

        override fun im(): ImMethodGroup = throw UnsupportedOperationException()

        override fun users(): UsersMethodGroup = throw UnsupportedOperationException()

        override fun oauth(): OauthMethodGroup = throw UnsupportedOperationException()

        override fun respond(): RespondMethodGroup = throw UnsupportedOperationException()

        override fun team(): TeamMethodGroup = throw UnsupportedOperationException()

        override fun usergroups(): UsergroupsMethodGroup = throw UnsupportedOperationException()
    }

}
