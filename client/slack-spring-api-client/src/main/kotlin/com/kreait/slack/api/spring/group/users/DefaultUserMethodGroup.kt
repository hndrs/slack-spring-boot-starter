package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.group.users.UserListMethod
import com.kreait.slack.api.group.users.UsersDeletePhotoMethod
import com.kreait.slack.api.group.users.UsersGetPresenceMethod
import com.kreait.slack.api.group.users.UsersGetProfileMethod
import com.kreait.slack.api.group.users.UsersInfoMethod
import com.kreait.slack.api.group.users.UsersLookupByEmailMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.group.users.UsersSetPhotoMethod
import com.kreait.slack.api.group.users.UsersSetPresenceMethod
import com.kreait.slack.api.group.users.UsersSetProfileMethod
import org.slf4j.LoggerFactory

class DefaultUserMethodGroup : UsersMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultUserMethodGroup::class.java)
    }

    override fun info(authToken: String): UsersInfoMethod {
        return DefaultUsersInfoMethod(authToken)
    }

    override fun list(authToken: String): UserListMethod {
        return DefaultUserListMethod(authToken)
    }

    override fun conversations(authToken: String): DefaultUserConversationsMethod {
        return DefaultUserConversationsMethod(authToken)
    }

    override fun deletePhoto(authToken: String): UsersDeletePhotoMethod {
        return DefaultUsersDeletePhotoMethod(authToken)
    }

    override fun identity(authToken: String): DefaultUsersIdentityMethod {
        return DefaultUsersIdentityMethod(authToken)
    }

    override fun setPresence(authToken: String): UsersSetPresenceMethod {
        return DefaultUsersSetPresenceMethod(authToken)
    }

    override fun getProfile(authToken: String): UsersGetProfileMethod {
        return DefaultUsersGetProfileMethod(authToken)
    }

    override fun setProfile(authToken: String): UsersSetProfileMethod {
        return DefaultUsersSetProfileMethod(authToken)
    }

    override fun setPhoto(authToken: String): UsersSetPhotoMethod {
        return DefaultUsersSetPhotoMethod(authToken)
    }

    override fun getPresence(authToken: String): UsersGetPresenceMethod {
        return DefaultUsersGetPresenceMethod(authToken)
    }

    override fun lookupByEmail(authToken: String): UsersLookupByEmailMethod {
        return DefaultUsersLookupByEmailMethod(authToken)
    }
}
