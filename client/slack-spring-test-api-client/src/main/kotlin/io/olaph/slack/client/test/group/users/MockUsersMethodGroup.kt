package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.users.UserConversationsMethod
import io.olaph.slack.client.group.users.UsersMethodGroup

class MockUsersMethodGroup : UsersMethodGroup {
    private val mockUsersInfoMethod = MockUsersInfoMethod()
    private val mockUserListMethod = MockUserListMethod()
    private val mockUserConversationsMethod = MockUserListMethod()


    override fun info(authToken: String): MockUsersInfoMethod {
        return mockUsersInfoMethod
    }

    override fun list(authToken: String): MockUserListMethod {
        return mockUserListMethod
    }

    override fun conversations(authToken: String): UserConversationsMethod {
return mockUserConversationsMethod
    }
}