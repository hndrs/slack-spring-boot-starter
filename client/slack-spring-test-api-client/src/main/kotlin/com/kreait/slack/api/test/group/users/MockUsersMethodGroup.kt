package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.group.users.UsersMethodGroup

/**
 * Testable implementation of [UsersMethodGroup]
 */
class MockUsersMethodGroup : UsersMethodGroup {

    private val mockUsersInfoMethod = MockUsersInfoMethod()
    private val mockUserListMethod = MockUserListMethod()
    private val mockUserListAllMethod = MockUserListAllMethod()
    private val mockUserConversationsMethod = MockUserConversationsMethod()
    private val mockUsersDeletePhotoMethod = MockUsersDeletePhotoMethod()
    private val mockUsersIdentityMethod = MockUsersIdentityMethod()
    private val mockUsersGetProfileMethod = MockUsersGetProfileMethod()
    private val mockUsersSetProfileMethod = MockUsersSetProfileMethod()
    private val mockUsersSetPresenceMethod = MockUsersSetPresenceMethod()
    private val mockUsersSetPhotoMethod = MockUsersSetPhotoMethod()
    private val mockUsersGetPresenceMethod = MockUsersGetPresenceMethod()
    private val mockUsersLookupByEmailMethod = MockUsersLookupByEmailMethod()

    override fun info(authToken: String) = mockUsersInfoMethod
    override fun list(authToken: String) = mockUserListMethod
    override fun listAll(authToken: String) = mockUserListAllMethod
    override fun conversations(authToken: String) = mockUserConversationsMethod
    override fun deletePhoto(authToken: String) = mockUsersDeletePhotoMethod
    override fun identity(authToken: String) = mockUsersIdentityMethod
    override fun getProfile(authToken: String) = mockUsersGetProfileMethod
    override fun setProfile(authToken: String) = mockUsersSetProfileMethod
    override fun setPresence(authToken: String) = mockUsersSetPresenceMethod
    override fun setPhoto(authToken: String) = mockUsersSetPhotoMethod
    override fun getPresence(authToken: String) = mockUsersGetPresenceMethod
    override fun lookupByEmail(authToken: String) = mockUsersLookupByEmailMethod
}
