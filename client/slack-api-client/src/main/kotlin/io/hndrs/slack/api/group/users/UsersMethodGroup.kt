package io.hndrs.slack.api.group.users

/**
 * Convenience class to handle the usergroup operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
@Suppress("ComplexInterface")
interface UsersMethodGroup {

    /**
     * Gets information about a user.
     */
    fun info(authToken: String): io.hndrs.slack.api.group.users.UsersInfoMethod

    /**
     * Lists users in a Slack team.
     */
    fun list(authToken: String): io.hndrs.slack.api.group.users.UserListMethod

    /**
     * Lists all users in a Slack team.
     */
    fun listAll(authToken: String): io.hndrs.slack.api.group.users.UserListAllMethod

    /**
     * List conversations the calling user may access.
     */
    fun conversations(authToken: String): io.hndrs.slack.api.group.users.UserConversationsMethod

    /**
     * Delete the user profile photo
     */
    fun deletePhoto(authToken: String): io.hndrs.slack.api.group.users.UsersDeletePhotoMethod

    /**
     * Get a user's identity.
     */
    fun identity(authToken: String): io.hndrs.slack.api.group.users.UsersIdentityMethod

    /**
     * Retrieves a user's profile information.
     */
    fun getProfile(authToken: String): io.hndrs.slack.api.group.users.UsersGetProfileMethod

    /**
     * Set the profile information for a user.
     */
    fun setProfile(authToken: String): io.hndrs.slack.api.group.users.UsersSetProfileMethod

    /**
     * Sets user presence information.
     */
    fun setPresence(authToken: String): io.hndrs.slack.api.group.users.UsersSetPresenceMethod

    /**
     * Set the user profile photo
     */
    fun setPhoto(authToken: String): io.hndrs.slack.api.group.users.UsersSetPhotoMethod

    /**
     * Gets user presence information.
     */
    fun getPresence(authToken: String): io.hndrs.slack.api.group.users.UsersGetPresenceMethod

    /**
     * Find a user with an email address.
     */
    fun lookupByEmail(authToken: String): io.hndrs.slack.api.group.users.UsersLookupByEmailMethod
}
