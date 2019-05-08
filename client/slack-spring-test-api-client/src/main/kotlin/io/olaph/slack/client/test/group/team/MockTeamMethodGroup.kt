package io.olaph.slack.client.test.group.team

import io.olaph.slack.client.group.team.TeamMethodGroup

class MockTeamMethodGroup : TeamMethodGroup {

    private val mockTeamGetProfileMethod = MockTeamGetProfileMethod()

    override fun getProfile(authToken: String): MockTeamGetProfileMethod {
        return mockTeamGetProfileMethod
    }
}