package com.kreait.slack.api.test.group.team

import com.kreait.slack.api.group.team.TeamMethodGroup

/**
 * Testable implementation of [TeamMethodGroup]
 */
class MockTeamMethodGroup : TeamMethodGroup {

    private val mockTeamGetProfileMethod = MockTeamGetProfileMethod()

    override fun getProfile(authToken: String) = mockTeamGetProfileMethod
}
