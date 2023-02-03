package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.methods.MethodsClient
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.event.SlackEvent
import io.hndrs.slack.broker.receiver.EventReceiver
import io.hndrs.slack.broker.receiver.InstallationReceiver
import io.hndrs.slack.broker.receiver.MismatchCommandReceiver
import io.hndrs.slack.broker.receiver.SL4JLoggingReceiver
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.hndrs.slack.broker.store.team.TeamStore
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
            .withConfiguration(AutoConfigurations.of(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java))
            .run {
                Assertions.assertDoesNotThrow { it.getBean(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java) }
            }
    }

    @DisplayName("Custom EvaluationReport Test")
    @Test
    fun customEvalReportTest() {
        TestApplicationContext.base()
            .withConfiguration(AutoConfigurations.of(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java))
            .withUserConfiguration(TestConfiguration::class.java)
            .run {
                val evaluationReport =
                    it.getBean(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java)
                        .buildEvaluationReport(it)
                assertThat(evaluationReport, containsString("testEventReceiver"))
                assertThat(evaluationReport, containsString("testSlashCommandReceiver"))
                assertThat(evaluationReport, containsString("testLoggingReceiver"))
                assertThat(evaluationReport, containsString("testMismatchCommandReceiver"))
            }
    }

    @DisplayName("EvaluationReport Default Notifications")
    @Test
    fun evaluationReportDefaultBeans() {
        TestApplicationContext.base()
            .withConfiguration(AutoConfigurations.of(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java))
            .withUserConfiguration(DefaultEventStoreConfiguration::class.java)
            .run {
                val evaluationReport =
                    it.getBean(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java)
                        .buildEvaluationReport(it)
                assertThat(
                    evaluationReport,
                    containsString("Default version of ${InMemoryEventStore::class.simpleName} is registered, this is not recommended for production")
                )
            }

        TestApplicationContext.base()
            .withConfiguration(AutoConfigurations.of(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java))
            .withUserConfiguration(DefaultTeamStoreConfiguration::class.java)
            .run {
                val evaluationReport =
                    it.getBean(io.hndrs.slack.broker.autoconfiguration.EvaluationReport::class.java)
                        .buildEvaluationReport(it)
                assertThat(
                    evaluationReport,
                    containsString("Default version of ${InMemoryTeamStore::class.simpleName} is registered, this is not recommended for production")
                )
            }
    }

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testEventReceiver() = object : EventReceiver {
            override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
                // stub
            }
        }

        @Bean
        open fun testInstallationReceiver(): InstallationReceiver = object : InstallationReceiver {
            override fun onInstallation(team: Team) {
                // stub
            }
        }

        @Bean
        open fun testSlashCommandReceiver(): SlashCommandReceiver = object : SlashCommandReceiver {
            override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
                // stub
            }
        }

        @Bean
        open fun testLoggingReceiver() = SL4JLoggingReceiver()

        @Bean
        open fun testMismatchCommandReceiver(): MismatchCommandReceiver = object : MismatchCommandReceiver {
            override fun onMismatchedSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team, methods: MethodsClient) {
                // stub
            }
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
