package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.api.contract.jackson.InteractiveMessage
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.receiver.InstallationReceiver
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.receiver.MismatchCommandReciever
import com.kreait.slack.broker.receiver.SL4JLoggingReceiver
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.EventStore
import com.kreait.slack.broker.store.InMemoryEventStore
import com.kreait.slack.broker.store.InMemoryTeamStore
import com.kreait.slack.broker.store.Team
import com.kreait.slack.broker.store.TeamStore
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

class EvaluationReportConfigurationTests {

    @DisplayName("EvaluationReport Registration Test")
    @Test
    fun evalReportRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java) }
                }
    }

    @DisplayName("Custom EvaluationReport Test")
    @Test
    fun customEvalReportTest() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .run {
                    val evaluationReport = it.getBean(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java).buildEvaluationReport(it)
                    assertThat(evaluationReport, containsString("testEventReceiver"))
                    assertThat(evaluationReport, containsString("testInteractiveComponentReceiver"))
                    assertThat(evaluationReport, containsString("testInteractiveComponentReceiver"))
                    assertThat(evaluationReport, containsString("testSlashCommandReceiver"))
                    assertThat(evaluationReport, containsString("testLoggingReceiver"))
                    assertThat(evaluationReport, containsString("testMismatchCommandReceiver"))
                }
    }

    @DisplayName("EvaluationReport Default Notifications")
    @Test
    fun evaluationReportDefaultBeans() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java))
                .withUserConfiguration(DefaultEventStoreConfiguration::class.java)
                .run {
                    val evaluationReport = it.getBean(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java).buildEvaluationReport(it)
                    assertThat(evaluationReport, containsString("Default version of ${InMemoryEventStore::class.simpleName} is registered, this is not recommended for production"))
                }

        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java))
                .withUserConfiguration(DefaultTeamStoreConfiguration::class.java)
                .run {
                    val evaluationReport = it.getBean(com.kreait.slack.broker.autoconfiguration.EvaluationReport::class.java).buildEvaluationReport(it)
                    assertThat(evaluationReport, containsString("Default version of ${InMemoryTeamStore::class.simpleName} is registered, this is not recommended for production"))
                }
    }

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testEventReceiver(): EventReceiver = object : EventReceiver {
            override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {}
        }

        @Bean
        open fun testInstallationReceiver(): InstallationReceiver = object : InstallationReceiver {
            override fun onReceiveInstallation(code: String, state: String, team: Team) {}
        }

        @Bean
        open fun testInteractiveComponentReceiver(): InteractiveComponentReceiver<InteractiveMessage> = object : InteractiveComponentReceiver<InteractiveMessage> {
            override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {}
        }

        @Bean
        open fun testSlashCommandReceiver(): SlashCommandReceiver = object : SlashCommandReceiver {
            override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {}
        }

        @Bean
        open fun testLoggingReceiver() = SL4JLoggingReceiver()

        @Bean
        open fun testMismatchCommandReceiver(): MismatchCommandReciever = object : MismatchCommandReciever {
            override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {}
        }
    }

    @Configuration
    open class DefaultTeamStoreConfiguration {

        @Bean
        open fun teamStore(): TeamStore = InMemoryTeamStore()
    }

    @Configuration
    open class DefaultEventStoreConfiguration {

        @Bean
        open fun eventStore(): EventStore = InMemoryEventStore()
    }

}
