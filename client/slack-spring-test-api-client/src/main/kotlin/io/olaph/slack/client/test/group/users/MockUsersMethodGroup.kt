package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.users.UsersMethodGroup

class MockUsersMethodGroup : UsersMethodGroup {
    private val mockUsersInfoMethod = MockUsersInfoMethod()
    private val mockUserListMethod = MockUserListMethod()
    private val mockUserConversationsMethod = MockUserConversationsMethod()
    private val mockUsersDeletePhotoMethod = MockUsersDeletePhotoMethod()
    private val mockUsersIdentityMethod = MockUsersIdentityMethod()
    private val mockUsersGetProfileMethod = MockUsersGetProfileMethod()
    private val mockUsersSetProfileMethod = MockUsersSetProfileMethod()
    private val mockUsersSetPresenceMethod = MockUsersSetPresenceMethod()
    private val mockUsersSetPhotoMethod = MockUsersSetPhotoMethod()
    private val mockUsersGetPresenceMethod = MockUsersGetPresenceMethod()
    private val mockUsersLookupByEmailMethod = MockUsersLookupByEmailMethod()

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

    override fun getProfile(authToken: String): MockUsersGetProfileMethod {
        return mockUsersGetProfileMethod
    }

    override fun setProfile(authToken: String): MockUsersSetProfileMethod {
        return mockUsersSetProfileMethod
    }

    override fun setPresence(authToken: String): MockUsersSetPresenceMethod {
        return mockUsersSetPresenceMethod
    }

    override fun setPhoto(authToken: String): MockUsersSetPhotoMethod {
        return mockUsersSetPhotoMethod
    }

    override fun getPresence(authToken: String): MockUsersGetPresenceMethod {
        return mockUsersGetPresenceMethod
    }

    override fun lookupByEmail(authToken: String): MockUsersLookupByEmailMethod {
        return mockUsersLookupByEmailMethod
    }
}
