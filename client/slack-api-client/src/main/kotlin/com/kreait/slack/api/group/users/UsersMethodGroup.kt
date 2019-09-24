package com.kreait.slack.api.group.users

/**
 * Convenience class to handle the usergroup operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface UsersMethodGroup {

    /**
     * Gets information about a user.
     */
    fun info(authToken: String): UsersInfoMethod

    /**
     * Lists users in a Slack team.
     */
    fun list(authToken: String): UserListMethod

    /**
     * Lists all users in a Slack team.
     */
    fun listAll(authToken: String): UserListAllMethod

    /**
     * List conversations the calling user may access.
     */
    fun conversations(authToken: String): UserConversationsMethod

    /**
     * Delete the user profile photo
     */
    fun deletePhoto(authToken: String): UsersDeletePhotoMethod

    /**
     * Get a user's identity.
     */
    fun identity(authToken: String): UsersIdentityMethod

    /**
     * Retrieves a user's profile information.
     */
    fun getProfile(authToken: String): UsersGetProfileMethod

    /**
     * Set the profile information for a user.
     */
    fun setProfile(authToken: String): UsersSetProfileMethod

    /**
     * Sets user presence information.
     */
    fun setPresence(authToken: String): UsersSetPresenceMethod

    /**
     * Set the user profile photo
     */
    fun setPhoto(authToken: String): UsersSetPhotoMethod

    /**
     * Gets user presence information.
     */
    fun getPresence(authToken: String): UsersGetPresenceMethod

    /**
     * Find a user with an email address.
     */
    fun lookupByEmail(authToken: String): UsersLookupByEmailMethod
}
