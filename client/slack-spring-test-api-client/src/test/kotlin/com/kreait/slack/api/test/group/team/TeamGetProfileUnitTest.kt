package com.kreait.slack.api.test.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.TeamGetProfileRequest
import com.kreait.slack.api.contract.jackson.group.team.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockTeamGetProfileMethod")
class TeamGetProfileUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulTeamGetProfileResponse?) -> Any = mock {}
        val failureFunction: (ErrorTeamGetProfileResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.team().getProfile("") },
                successFunction, SuccessfulTeamGetProfileResponse.sample(),
                failureFunction, ErrorTeamGetProfileResponse.sample(),
                TeamGetProfileRequest.sample()
        )
    }
}
