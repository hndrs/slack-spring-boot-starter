package com.kreait.slack.broker.store.user

import com.kreait.slack.api.contract.jackson.common.types.Member
import java.time.Instant

/**
 * Used to handle App-installations
 */
interface UserStore {

    /**
     * returns a [User] by its Id
     * @param id the userId you want to find a [User] for
     */
    fun findById(id: String): User

    /**
     * Adds a [User] to the [UserStore]
     *
     * @param users the [User]s you want to add
     */
    fun put(vararg users: User)

    /**
     * returns all [User]s of a Workspace/Team
     * @param teamId the teamId you want to find [User]s for
     * @param includeDeleted determines whether deleted [User]s should be included
     * defaults to [false]
     */
    fun findByTeam(teamId: String, includeDeleted: Boolean = false): List<User>

    /**
     * updates a [User]
     * @param newUser the updated [User]
     */
    fun update(newUser: User)

    fun findAll(): List<User>
}

/**
 * creates a [User] object out of a [Member]
 */
fun userOfMember(member: Member): User {
    return User(member.id, member.teamId, member.name, member.isDeleted, member.color, member.realName,
            member.timezone, member.timezoneLabel, member.timezoneOffset,
            User.UserProfile(member.profile.title, member.profile.phone, member.profile.skype,
                    member.profile.realName, member.profile.realNameNormalized, member.profile.displayName,
                    member.profile.displayNameNormalized, member.profile.fields, member.profile.statusText,
                    member.profile.statusEmoji, member.profile.statusExpiration, member.profile.avatarHash,
                    member.profile.alwaysActive, member.profile.imageOriginal, member.profile.email,
                    member.profile.firstName, member.profile.lastName, member.profile.image24, member.profile.image32,
                    member.profile.image48, member.profile.image72, member.profile.image192, member.profile.image512,
                    member.profile.image1024, member.profile.statusTextCanonical, member.profile.team),
            member.isAdmin, member.isOwner, member.isPrimaryOwner, member.isRestricted, member.isUltraRestricted, member.isBot,
            member.lastModifiedAt, member.isAppUser, member.has2fa, member.locale)
}

/**
 * creates a [User] object out of a [FileUserStore.LocalUser]
 *
 * @param localUser
 * @return
 */
fun userOfLocalUser(localUser: FileUserStore.LocalUser): User {
    return User(localUser.id, localUser.teamId, localUser.name, localUser.isDeleted, localUser.color, localUser.realName,
            localUser.timezone, localUser.timezoneLabel, localUser.timezoneOffset,
            User.UserProfile(localUser.profile.title, localUser.profile.phone, localUser.profile.skype, localUser.profile.realName,
                    localUser.profile.realNameNormalized, localUser.profile.displayName, localUser.profile.displayNameNormalized,
                    localUser.profile.fields, localUser.profile.statusText, localUser.profile.statusEmoji,
                    localUser.profile.statusExpiration, localUser.profile.avatarHash, localUser.profile.alwaysActive,
                    localUser.profile.imageOriginal, localUser.profile.email, localUser.profile.firstName, localUser.profile.lastName,
                    localUser.profile.image24, localUser.profile.image32, localUser.profile.image48, localUser.profile.image72,
                    localUser.profile.image192, localUser.profile.image512, localUser.profile.image1024,
                    localUser.profile.statusTextCanonical, localUser.profile.team)
            , localUser.isAdmin,
            localUser.isOwner, localUser.isPrimaryOwner, localUser.isRestricted, localUser.isUltraRestricted, localUser.isBot,
            localUser.lastModifiedAt, localUser.isAppUser, localUser.has2fa, localUser.locale)
}

/**
 * Exception that is thrown when the requested user does not exist
 *
 * @property message detailed error description
 */
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
    /**
     * describes the profile of a user
     */
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
            val imageOriginal: String?,
            val email: String?,
            val firstName: String?,
            val lastName: String?,
            val image24: String?,
            val image32: String?,
            val image48: String?,
            val image72: String?,
            val image192: String?,
            val image512: String?,
            val image1024: String?,
            val statusTextCanonical: String,
            val team: String
    ) {
        companion object
    }
}

