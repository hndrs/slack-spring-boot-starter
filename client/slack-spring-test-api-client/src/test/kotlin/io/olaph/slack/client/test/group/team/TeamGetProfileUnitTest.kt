package io.olaph.slack.client.test.group.team

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.team.ErrorTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.SuccessfulTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.TeamGetProfileRequest
import io.olaph.slack.dto.jackson.group.team.sample
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
