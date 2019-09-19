package com.kreait.slack.broker.store.user

import java.time.Instant

/**
 * Used to handle App-installations
 */
interface UserStore {

    /**
     * returns a User by its Id
     */
    fun findById(id: String): User

    /**
     * Adds a User to the Database
     */
    fun put(vararg users: User)

    /**
     * returns all users of a Workspace/Team
     */
    fun findByTeam(teamId: String): List<User>

    /**
     * removes a User by its Id
     */
    fun removeById(id: String)
}

class UserNotFoundException(override val message: String?) : RuntimeException(message)

/**
 * describes a Team that installed your App
 */
data class User(val id: String,
                val teamId: String,
                val name: String,
                val isDeleted: Boolean,
                val color: String?,
                val realName: String?,
                val timezone: String?,
                val timezoneLabel: String?,
                val timezoneOffset: Int?,
                val profile: UserProfile,
                val isAdmin: Boolean,
                val isOwner: Boolean,
                val isPrimaryOwner: Boolean,
                val isRestricted: Boolean,
                val isUltraRestricted: Boolean,
                val isBot: Boolean,
                val lastModifiedAt: Instant? = null,
                val isAppUser: Boolean,
                val has2fa: Boolean,
                val locale: String?) {

    companion object {}
    data class UserProfile(
            val title: String?,
            val phone: String?,
            val skype: String?,
            val realName: String?,
            val realNameNormalized: String?,
            val displayName: String,
            val displayNameNormalized: String,
            val fields: Map<Any, Any>?,
            val statusText: String,
            val statusEmoji: String,
            val statusExpiration: Int,
            val avatarHash: String?,
            val alwaysActive: Boolean,
            val image_original: String?,
            val email: String?,
            val firstName: String?,
            val lastName: String?,
            val image24: String?,
            val image32: String?,
            val image48: String?,
            val image72: String?,
            val image192: String?,
            val image512: String?,
            val image_1024: String?,
            val statusTextCanonical: String,
            val team: String
    ) {
        companion object
    }
}

