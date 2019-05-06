package io.olaph.slack.client.test.group.team

import io.olaph.slack.client.group.team.TeamGetProfileMethod
import io.olaph.slack.client.group.team.TeamMethodGroup

class MockTeamMethodGroup : TeamMethodGroup {
    val mockTeamGetProfileMethod = MockTeamGetProfileMethod()

    override fun getProfile(authToken: String): TeamGetProfileMethod {
        return mockTeamGetProfileMethod
    }

}