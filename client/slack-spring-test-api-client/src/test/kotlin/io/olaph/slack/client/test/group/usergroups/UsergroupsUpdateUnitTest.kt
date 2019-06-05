package io.olaph.slack.client.test.group.usergroups

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.update Method")
class UsergroupsUpdateUnitTest {

    @DisplayName("Mock Successfuk")
    @Test
    fun mockSuccess() {
        val successFunction: (SuccessfulUsergroupsUpdateResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupsUpdateResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().update("") },
                successFunction, SuccessfulUsergroupsUpdateResponse.sample(),
                failureFunction, ErrorUsergroupsUpdateResponse.sample(),
                SlackUsergroupsUpdateRequest.sample())
    }
}