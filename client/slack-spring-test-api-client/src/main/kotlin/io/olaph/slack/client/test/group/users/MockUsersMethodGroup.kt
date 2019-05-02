package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.users.UsersMethodGroup

class MockUsersMethodGroup : UsersMethodGroup {
    private val mockUsersInfoMethod = MockUsersInfoMethod()
    private val mockUserListMethod = MockUserListMethod()
    private val mockUserConversationsMethod = MockUserConversationsMethod()
    private val mockUsersDeletePhotoMethod = MockUsersDeletePhotoMethod()
    private val mockUsersIdentityMethod = MockUsersIdentityMethod()

    override fun info(authToken: String): MockUsersInfoMethod {
        return mockUsersInfoMethod
    }

    override fun list(authToken: String): MockUserListMethod {
        return mockUserListMethod
    }

    override fun conversations(authToken: String): MockUserConversationsMethod {
        return mockUserConversationsMethod
    }

    override fun deletePhoto(authToken: String): MockUsersDeletePhotoMethod {
        return mockUsersDeletePhotoMethod
    }

    override fun identity(authToken: String): MockUsersIdentityMethod {
        return mockUsersIdentityMethod
    }
}
