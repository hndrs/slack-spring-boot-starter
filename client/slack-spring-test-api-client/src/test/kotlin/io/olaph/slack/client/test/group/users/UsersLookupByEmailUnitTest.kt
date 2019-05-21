package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersLookupByEmailRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("users.lookupByEmail Method")
class UsersLookupByEmailUnitTest {

    @DisplayName("users.lookupByEmail Test")
    @Test
    fun usersLookupByEmailTest() {

        val successFunction: (SuccessfulUsersLookupByEmailResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsersLookupByEmailResponse?) -> Any = mock { }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.users().lookupByEmail("") },
                successFunction, SuccessfulUsersLookupByEmailResponse.sample(),
                failureFunction, ErrorUsersLookupByEmailResponse.sample(),
                SlackUsersLookupByEmailRequest.sample())
    }
}