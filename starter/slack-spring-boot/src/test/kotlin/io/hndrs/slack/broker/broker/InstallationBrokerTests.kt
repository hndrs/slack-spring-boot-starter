package io.hndrs.slack.broker.broker

import com.slack.api.RequestConfigurator
import com.slack.api.Slack
import com.slack.api.methods.request.oauth.OAuthV2AccessRequest
import com.slack.api.methods.response.oauth.OAuthV2AccessResponse
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.installation.InstallationBroker
import io.hndrs.slack.broker.installation.InstallationReceiver
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Installation Test Broker Tests")
class InstallationBrokerTests {

    @Test
    @DisplayName("Successful Installation")
    fun successfulInstallation() {
        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        val mockSlackClient = mockk<Slack>() {
            every {
                methods().oauthV2Access(any<RequestConfigurator<OAuthV2AccessRequest.OAuthV2AccessRequestBuilder>>())
            } returns OAuthV2AccessResponse().apply {
                isOk = true
                team = OAuthV2AccessResponse.Team().apply {
                    this.id = "id"
                    this.name = "name"
                }
                accessToken = "accessToken"
            }

            every { methods(any(), any()) } returns mockk()
        }

        InstallationBroker(listOf(successReceiver, errorReceiver), teamStore, INSTALLATION_CONFIG, mockSlackClient)
            .onInstall("code", "state")

        InstallationBroker(listOf(successReceiver, errorReceiver), teamStore, INSTALLATION_CONFIG, mockSlackClient)
            .onInstall("code", "state")

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("Error Installation")
    fun errorInstallation() {
        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        val mockSlackClient = mockk<Slack>() {
            every {
                methods().oauthV2Access(any<RequestConfigurator<OAuthV2AccessRequest.OAuthV2AccessRequestBuilder>>())
            } returns
                    OAuthV2AccessResponse().apply {
                        isOk = false
                    }
        }

        InstallationBroker(listOf(successReceiver, errorReceiver), teamStore, INSTALLATION_CONFIG, mockSlackClient)
            .onInstall("code", "state")

        InstallationBroker(listOf(successReceiver, errorReceiver), teamStore, INSTALLATION_CONFIG, mockSlackClient)
            .onInstall("code", "state")

        Assertions.assertFalse(successReceiver.executed)
        Assertions.assertFalse(errorReceiver.executed)
    }

    class SuccessReceiver : InstallationReceiver {

        var executed: Boolean = false

        override fun onInstallation(team: Team) {
            executed = true
        }
    }

    class ErrorReceiver : InstallationReceiver {

        var executed: Boolean = false

        override fun onInstallation(team: Team) {
            executed = true
            error("Failure Test")
        }
    }

    private companion object {
        val INSTALLATION_CONFIG = InstallationBroker.Config("someClientId", "", "", "")
    }
}
