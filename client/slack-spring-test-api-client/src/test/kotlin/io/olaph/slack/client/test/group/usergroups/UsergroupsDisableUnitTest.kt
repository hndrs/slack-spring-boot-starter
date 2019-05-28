package io.olaph.slack.client.test.group.usergroups

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsDisableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.disable Method")
class UsergroupsDisableUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {

        val successFunction: (SuccessfulUsergroupsDisableResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsergroupsDisableResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.usergroups().disable("") },
                successFunction, SuccessfulUsergroupsDisableResponse.sample(),
                failureFunction, ErrorUsergroupsDisableResponse.sample(),
                SlackUsergroupsDisableRequest.sample())
    }
}