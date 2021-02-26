package io.hndrs.slack.broker.broker

import io.hndrs.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.sample
import io.hndrs.slack.api.test.MockSlackClient
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.metrics.InstallationMetrics
import io.hndrs.slack.broker.receiver.InstallationReceiver
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Installation Test Broker Tests")
class InstallationBrokerTests {


    @Test
    @DisplayName("SuccessFul Installation")
    fun successfulInstallation() {


        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))
        val metrics = InstallationMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()


        val mockSlackClient = MockSlackClient()


        mockSlackClient.oauth().access().successResponse = SuccessfullAccessResponse.sample()

        InstallationBroker(listOf(successReceiver, errorReceiver), metrics, teamStore, mockSlackClient, InstallationBroker.Config("someClientId", "", "", ""))
                .onInstall("code", "state")

        InstallationBroker(listOf(successReceiver, errorReceiver), null, teamStore, mockSlackClient, InstallationBroker.Config("someClientId", "", "", ""))
                .onInstall("code", "state")

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)

        Assertions.assertEquals(1.0, meterRegistry.get("slack.installation.success").counter().count())
        Assertions.assertEquals(0.0, meterRegistry.get("slack.installation.error").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.installation.count").counter().count())
        Assertions.assertEquals(2.0, meterRegistry.get("slack.installation.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.installation.receiver.errors").counter().count())
    }

    @Test
    @DisplayName("Error Installation")
    fun errorInstallation() {


        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val metrics = InstallationMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()


        val mockSlackClient = MockSlackClient()

        mockSlackClient.oauth().access().failureResponse = ErrorAccessResponse.sample()

        InstallationBroker(listOf(successReceiver, errorReceiver), metrics, teamStore, mockSlackClient, InstallationBroker.Config("someClientId", "", "", ""))
                .onInstall("code", "state")

        InstallationBroker(listOf(successReceiver, errorReceiver), null, teamStore, mockSlackClient, InstallationBroker.Config("someClientId", "", "", ""))
                .onInstall("code", "state")

        Assertions.assertFalse(successReceiver.executed)
        Assertions.assertFalse(errorReceiver.executed)

        Assertions.assertEquals(0.0, meterRegistry.get("slack.installation.success").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.installation.error").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.installation.count").counter().count())
        Assertions.assertEquals(0.0, meterRegistry.get("slack.installation.receiver.executions").counter().count())
        Assertions.assertEquals(0.0, meterRegistry.get("slack.installation.receiver.errors").counter().count())
    }


    class SuccessReceiver : InstallationReceiver {

        var executed: Boolean = false

        override fun onReceiveInstallation(code: String, state: String, team: Team) {
            executed = true
        }
    }

    class ErrorReceiver : InstallationReceiver {

        var executed: Boolean = false

        override fun onReceiveInstallation(code: String, state: String, team: Team) {
            executed = true
            throw IllegalStateException("Failure Test")
        }
    }
}
