package io.olaph.slack.client.test.group.usergroups

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsEnableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.enable Method")
class UsergroupsEnableUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun mockUsergroupsEnable() {

        val successFunction: (SuccessfulUsergroupsEnableResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsergroupsEnableResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.usergroups().enable("") },
                successFunction, SuccessfulUsergroupsEnableResponse.sample(),
                failureFunction, ErrorUsergroupsEnableResponse.sample(),
                SlackUsergroupsEnableRequest.sample())
    }
}