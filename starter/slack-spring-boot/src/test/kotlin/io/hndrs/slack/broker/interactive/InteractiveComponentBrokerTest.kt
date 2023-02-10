package io.hndrs.slack.broker.interactive

import com.slack.api.util.json.GsonFactory
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.interactive.views.ViewClosedPayload
import io.hndrs.slack.broker.interactive.views.ViewClosedReceiver
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

class InteractiveComponentBrokerTest {

    private val team = Team.sample()
    private val testBlockAction = TestBlockAction()
    private val testViewClosed = TestViewClosed()

    private val underTest: InteractiveComponentBroker = InteractiveComponentBroker(
        blockActionReceivers = setOf(testBlockAction),
        viewClosedReceivers = setOf(testViewClosed),
        teamStore = InMemoryTeamStore().apply {
            put(team)
        },
        gson = spyk(GsonFactory.createSnakeCase()) {
            every { fromJson(any<String>(), BlockActionPayload::class.java) } returns mockk()
            every { fromJson(any<String>(), ViewClosedPayload::class.java) } returns mockk()
        }
    )

    @Test
    fun `execute block action`() {

        underTest.receiveCommand(
            """
            {
                "type" : "${BlockActionPayload.TYPE}",
                "team" : {
                    "id" : "${team.teamId}"
                }
            }
            """.trimIndent(),
            HttpHeaders()
        )

        testBlockAction.executed shouldBe true
    }

    @Test
    fun `execute view closed`() {

        underTest.receiveCommand(
            """
            {
                "type" : "${ViewClosedPayload.TYPE}",
                "team" : {
                    "id" : "${team.teamId}"
                }
            }
            """.trimIndent(),
            HttpHeaders()
        )

        testViewClosed.executed shouldBe true
    }


    internal class TestBlockAction : BlockActionReceiver {
        var executed = false
        override fun onBlockAction(payload: BlockActionPayload, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }

    internal class TestViewClosed : ViewClosedReceiver {
        var executed = false
        override fun onViewClosed(payload: ViewClosedPayload, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }
}
