package io.hndrs.slack.api.spring.group.users

import io.hndrs.slack.api.group.users.UserListAllMethod
import io.hndrs.slack.api.group.users.UserListMethod
import io.hndrs.slack.api.group.users.UsersDeletePhotoMethod
import io.hndrs.slack.api.group.users.UsersGetPresenceMethod
import io.hndrs.slack.api.group.users.UsersGetProfileMethod
import io.hndrs.slack.api.group.users.UsersInfoMethod
import io.hndrs.slack.api.group.users.UsersLookupByEmailMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.group.users.UsersSetPhotoMethod
import io.hndrs.slack.api.group.users.UsersSetPresenceMethod
import io.hndrs.slack.api.group.users.UsersSetProfileMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api Users method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringUserMethodGroup : io.hndrs.slack.api.group.users.UsersMethodGroup {

    override fun info(authToken: String): io.hndrs.slack.api.group.users.UsersInfoMethod {
        return SpringUsersInfoMethod(authToken)
    }

    override fun list(authToken: String): io.hndrs.slack.api.group.users.UserListMethod {
        return SpringUserListMethod(authToken)
    }

    override fun listAll(authToken: String): io.hndrs.slack.api.group.users.UserListAllMethod {
        return SpringUserListAllMethod(authToken)
    }

    override fun conversations(authToken: String): SpringUserConversationsMethod {
        return SpringUserConversationsMethod(authToken)
    }

    override fun deletePhoto(authToken: String): io.hndrs.slack.api.group.users.UsersDeletePhotoMethod {
        return SpringUsersDeletePhotoMethod(authToken)
    }

    override fun identity(authToken: String): SpringUsersIdentityMethod {
        return SpringUsersIdentityMethod(authToken)
    }

    override fun setPresence(authToken: String): io.hndrs.slack.api.group.users.UsersSetPresenceMethod {
        return SpringUsersSetPresenceMethod(authToken)
    }

    override fun getProfile(authToken: String): io.hndrs.slack.api.group.users.UsersGetProfileMethod {
        return SpringUsersGetProfileMethod(authToken)
    }

    override fun setProfile(authToken: String): io.hndrs.slack.api.group.users.UsersSetProfileMethod {
        return SpringUsersSetProfileMethod(authToken)
    }

    override fun setPhoto(authToken: String): io.hndrs.slack.api.group.users.UsersSetPhotoMethod {
        return SpringUsersSetPhotoMethod(authToken)
    }

    override fun getPresence(authToken: String): io.hndrs.slack.api.group.users.UsersGetPresenceMethod {
        return SpringUsersGetPresenceMethod(authToken)
    }

    override fun lookupByEmail(authToken: String): io.hndrs.slack.api.group.users.UsersLookupByEmailMethod {
        return SpringUsersLookupByEmailMethod(authToken)
    }
}
