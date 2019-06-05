package io.olaph.slack.client.test.group.usergroups

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsListRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.list Method")
class UsergroupsListUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulUsergroupsListResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupsListResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().list("") },
                successFunction, SuccessfulUsergroupsListResponse.sample(),
                failureFunction, ErrorUsergroupsListResponse.sample(),
                SlackUsergroupsListRequest.sample())
    }
}