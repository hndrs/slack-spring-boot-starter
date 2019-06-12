package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.MismatchCommandReciever
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.dto.jackson.InteractiveComponentResponse
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.SlackEvent
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
                .withConfiguration(AutoConfigurations.of(EvaluationReport::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(EvaluationReport::class.java) }
                }
    }

    @DisplayName("Custom EvaluationReport Test")
    @Test
    fun customEvalReportTest() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(EvaluationReport::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .run {
                    val evaluationReport = it.getBean(EvaluationReport::class.java).buildEvaluationReport(it)
                    assertThat(evaluationReport, containsString("testEventReceiver"))
                    assertThat(evaluationReport, containsString("testInteractiveComponentReceiver"))
                    assertThat(evaluationReport, containsString("testInteractiveComponentReceiver"))
                    assertThat(evaluationReport, containsString("testSlashCommandReceiver"))
                    assertThat(evaluationReport, containsString("testLoggingReceiver"))
                    assertThat(evaluationReport, containsString("testMismatchCommandReceiver"))
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
        open fun testInteractiveComponentReceiver(): InteractiveComponentReceiver = object : InteractiveComponentReceiver {
            override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {}
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
}