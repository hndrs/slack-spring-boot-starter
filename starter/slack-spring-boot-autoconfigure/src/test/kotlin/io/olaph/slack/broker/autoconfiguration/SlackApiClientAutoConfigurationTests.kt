package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.group.auth.AuthGroup
import io.olaph.slack.client.group.channels.ChannelsMethodGroup
import io.olaph.slack.client.group.chat.ChatMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.dialog.DialogMethodGroup
import io.olaph.slack.client.group.im.ImMethodGroup
import io.olaph.slack.client.group.oauth.OauthMethodGroup
import io.olaph.slack.client.group.respond.RespondMethodGroup
import io.olaph.slack.client.group.team.TeamMethodGroup
import io.olaph.slack.client.group.usergroups.UsergroupsMethodGroup
import io.olaph.slack.client.group.users.UsersMethodGroup
import io.olaph.slack.client.spring.DefaultSlackClient
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
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(SlackClient::class.java) }
                    Assertions.assertTrue(it.getBean(SlackClient::class.java) is DefaultSlackClient)
                }
    }

    @DisplayName("Custom SlackClient Registration")
    @Test
    fun customSlackClientRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
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
