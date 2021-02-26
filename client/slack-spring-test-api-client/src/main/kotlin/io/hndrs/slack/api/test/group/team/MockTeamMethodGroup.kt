package io.hndrs.slack.api.test.group.team

import io.hndrs.slack.api.group.team.TeamMethodGroup

/**
 * Testable implementation of [TeamMethodGroup]
 */
class MockTeamMethodGroup : io.hndrs.slack.api.group.team.TeamMethodGroup {

    private val mockTeamGetProfileMethod = MockTeamGetProfileMethod()

    override fun getProfile(authToken: String) = mockTeamGetProfileMethod
}
