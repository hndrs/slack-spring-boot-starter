package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.group.users.UserListAllMethod
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

/**
 * Convenience function to apply slack api Users method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringUserMethodGroup : UsersMethodGroup {

    override fun info(authToken: String): UsersInfoMethod {
        return SpringUsersInfoMethod(authToken)
    }

    override fun list(authToken: String): UserListMethod {
        return SpringUserListMethod(authToken)
    }

    override fun listAll(authToken: String): UserListAllMethod {
        return SpringUserListAllMethod(authToken)
    }

    override fun conversations(authToken: String): SpringUserConversationsMethod {
        return SpringUserConversationsMethod(authToken)
    }

    override fun deletePhoto(authToken: String): UsersDeletePhotoMethod {
        return SpringUsersDeletePhotoMethod(authToken)
    }

    override fun identity(authToken: String): SpringUsersIdentityMethod {
        return SpringUsersIdentityMethod(authToken)
    }

    override fun setPresence(authToken: String): UsersSetPresenceMethod {
        return SpringUsersSetPresenceMethod(authToken)
    }

    override fun getProfile(authToken: String): UsersGetProfileMethod {
        return SpringUsersGetProfileMethod(authToken)
    }

    override fun setProfile(authToken: String): UsersSetProfileMethod {
        return SpringUsersSetProfileMethod(authToken)
    }

    override fun setPhoto(authToken: String): UsersSetPhotoMethod {
        return SpringUsersSetPhotoMethod(authToken)
    }

    override fun getPresence(authToken: String): UsersGetPresenceMethod {
        return SpringUsersGetPresenceMethod(authToken)
    }

    override fun lookupByEmail(authToken: String): UsersLookupByEmailMethod {
        return SpringUsersLookupByEmailMethod(authToken)
    }
}
