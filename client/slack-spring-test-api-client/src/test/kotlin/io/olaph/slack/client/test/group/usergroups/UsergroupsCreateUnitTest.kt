package io.olaph.slack.client.test.group.usergroups

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsCreateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock UsergroupsCreate Method")
class UsergroupsCreateUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {

        val successFunction: (SuccessfulUsergroupsCreateResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsergroupsCreateResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.usergroups().create("") },
                successFunction, SuccessfulUsergroupsCreateResponse.sample(),
                failureFunction, ErrorUsergroupsCreateResponse.sample(),
                SlackUsergroupsCreateRequest.sample())
    }
}