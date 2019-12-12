package com.kreait.slack.broker.store.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.charset.Charset
import java.time.Instant

/**
 * Default implementation of a local UserStore
 * This implementation is meant for develop environments
 */
class FileUserStore : UserStore {

    companion object {

        private val LOG = LoggerFactory.getLogger(FileUserStore::class.java)

        private const val fileName = "user-store.json"
        private val objectMapper = jacksonObjectMapper()

        /**
         * Methods to set up the directory and the user-storage file
         */
        private fun homeDirectory(): String = System.getProperty("user.home")
                ?: throw IllegalArgumentException("Unable to load users-file:'user.home' System property is not set.")

        private fun dataFile(): File = File(homeDirectory(), ".slack/$fileName")

    }

    /**
     * File should be already present.
     * Creates the user-store file in case it is missing
     */
    init {
        if (!dataFile().exists()) {
            LOG.info("Did not find user-file under ${dataFile().absolutePath}. Attempting to create it")
            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()
            objectMapper.writeValue(dataFile(), listOf<LocalUser>())
            LOG.info("$fileName successfully created under ${dataFile().absolutePath}")
        } else if (dataFile().exists() && dataFile().isFile) {
            LOG.info("$fileName found")
            if (dataFile().length() == 0L) {
                LOG.info("UserStore-file is empty, initializing with emtpty array")
                objectMapper.writeValue(dataFile(), listOf<LocalUser>())
            }
        } else {
            LOG.error("Could not create UserStore-file")
            throw IllegalStateException("$fileName seems to be a directory")
        }
    }

    /**
     * Operations on the local file
     */
    override fun findById(id: String): User {
        val localUsers: List<LocalUser> = objectMapper.readValue(dataFile())

        // Find the user with the given id in the list that was retrieved from the file
        val localUser = localUsers.find { it.id == id } ?: throw UserNotFoundException("User $id not found.")

        return userOfLocalUser(localUser)
    }

    override fun findByTeam(teamId: String, includeDeleted: Boolean): List<User> {
        val localUsers: List<LocalUser> = objectMapper.readValue(dataFile())
        //filter users by team id
        return localUsers.filter { it.teamId == teamId && !it.isDeleted }.map { userOfLocalUser(it) }
    }

    override fun put(vararg users: User) {

        LOG.info("Inserting users")
        val file = dataFile()

        val origin: List<LocalUser> = objectMapper.readValue(file)
        val localUsers = mutableListOf<LocalUser>()
        // Build an Object out of the user that is given in via parameter
        users.forEach { localUsers.add(LocalUser.of(it)) }

        val result = origin.plus(localUsers)
        FileUtils.write(file, objectMapper.writeValueAsString(result), Charset.forName("UTF-8"))
    }

    override fun update(newUser: User) {
        val origin: List<LocalUser> = objectMapper.readValue(dataFile())
        val userToRemove = origin.find { it.id == newUser.id }
        userToRemove?.let {
            objectMapper.writeValue(dataFile(), origin.minus(it))
        }
        put(newUser)
    }

    override fun findAll(): List<User> = objectMapper.readValue<List<LocalUser>>(dataFile()).map { userOfLocalUser(it) }

    /**
     * The user-object that is saved to the file
     */
    data class LocalUser(@field:JsonProperty("id")
                         @get:JsonProperty("id")
                         val id: String,
                         @field:JsonProperty("team_id")
                         @get:JsonProperty("team_id")
                         val teamId: String,
                         @field:JsonProperty("name")
                         @get:JsonProperty("name")
                         val name: String,
                         @field:JsonProperty("deleted")
                         @get:JsonProperty("deleted")
                         val isDeleted: Boolean,
                         @field:JsonProperty("color")
                         @get:JsonProperty("color")
                         val color: String?,
                         @field:JsonProperty("real_name")
                         @get:JsonProperty("real_name")
                         val realName: String?,
                         @field:JsonProperty("tz")
                         @get:JsonProperty("tz")
                         val timezone: String?,
                         @field:JsonProperty("tz_label")
                         @get:JsonProperty("tz_label")
                         val timezoneLabel: String?,
                         @field:JsonProperty("tz_offset")
                         @get:JsonProperty("tz_offset")
                         val timezoneOffset: Int?,
                         @field:JsonProperty("profile")
                         @get:JsonProperty("profile")
                         val profile: UserProfile,
                         @field:JsonProperty("is_admin")
                         @get:JsonProperty("is_admin")
                         val isAdmin: Boolean,
                         @field:JsonProperty("is_owner")
                         @get:JsonProperty("is_owner")
                         val isOwner: Boolean,
                         @field:JsonProperty("is_primary_owner")
                         @get:JsonProperty("is_primary_owner")
                         val isPrimaryOwner: Boolean,
                         @field:JsonProperty("is_restricted")
                         @get:JsonProperty("is_restricted")
                         val isRestricted: Boolean,
                         @field:JsonProperty("is_ultra_restricted")
                         @get:JsonProperty("is_ultra_restricted")
                         val isUltraRestricted: Boolean,
                         @field:JsonProperty("is_bot")
                         @get:JsonProperty("is_bot")
                         val isBot: Boolean,
                         @InstantToInt @field:JsonProperty("updated")
                         @get:JsonProperty("updated")
                         val lastModifiedAt: Instant? = null,
                         @field:JsonProperty("is_app_user")
                         @get:JsonProperty("is_app_user")
                         val isAppUser: Boolean,
                         @field:JsonProperty("has_2fa")
                         @get:JsonProperty("has_2fa")
                         val has2fa: Boolean,
                         @field:JsonProperty("locale")
                         @get:JsonProperty("locale")
                         val locale: String?) {

        /**
         * The UserProfile that is saved to the file
         */
        @JacksonDataClass
        data class UserProfile(
                @field:JsonProperty("title")
                @get:JsonProperty("title")
                val title: String?,
                @field:JsonProperty("phone")
                @get:JsonProperty("phone")
                val phone: String?,
                @field:JsonProperty("skype")
                @get:JsonProperty("skype")
                val skype: String?,
                @field:JsonProperty("real_name")
                @get:JsonProperty("real_name")
                val realName: String?,
                @field:JsonProperty("real_name_normalized")
                @get:JsonProperty("real_name_normalized")
                val realNameNormalized: String?,
                @field:JsonProperty("display_name")
                @get:JsonProperty("display_name")
                val displayName: String,
                @field:JsonProperty("display_name_normalized")
                @get:JsonProperty("display_name_normalized")
                val displayNameNormalized: String,
                @field:JsonProperty("fields")
                @get:JsonProperty("fields")
                val fields: Map<Any, Any>?,
                @field:JsonProperty("status_text")
                @get:JsonProperty("status_text")
                val statusText: String,
                @field:JsonProperty("status_emoji")
                @get:JsonProperty("status_emoji")
                val statusEmoji: String,
                @field:JsonProperty("status_expiration")
                @get:JsonProperty("status_expiration")
                val statusExpiration: Int,
                @field:JsonProperty("avatar_hash")
                @get:JsonProperty("avatar_hash")
                val avatarHash: String?,
                @field:JsonProperty("always_active")
                @get:JsonProperty("always_active")
                val alwaysActive: Boolean,
                @field:JsonProperty("image_original")
                @get:JsonProperty("image_original")
                val imageOriginal: String?,
                @field:JsonProperty("email")
                @get:JsonProperty("email")
                val email: String?,
                @field:JsonProperty("first_name")
                @get:JsonProperty("first_name")
                val firstName: String?,
                @field:JsonProperty("last_name")
                @get:JsonProperty("last_name")
                val lastName: String?,
                @field:JsonProperty("image_24")
                @get:JsonProperty("image_24")
                val image24: String?,
                @field:JsonProperty("image_32")
                @get:JsonProperty("image_32")
                val image32: String?,
                @field:JsonProperty("image_48")
                @get:JsonProperty("image_48")
                val image48: String?,
                @field:JsonProperty("image_72")
                @get:JsonProperty("image_72")
                val image72: String?,
                @field:JsonProperty("image_192")
                @get:JsonProperty("image_192")
                val image192: String?,
                @field:JsonProperty("image_512")
                @get:JsonProperty("image_512")
                val image512: String?,
                @field:JsonProperty("image_1024")
                @get:JsonProperty("image_1024")
                val image1024: String?,
                @field:JsonProperty("status_text_canonical")
                @get:JsonProperty("status_text_canonical")
                val statusTextCanonical: String,
                @field:JsonProperty("team")
                @get:JsonProperty("team")
                val team: String
        ) {
            companion object {
                /**
                 * creates a [LocalUser.UserProfile] out of an [User.UserProfile]
                 */
                fun of(userProfile: User.UserProfile): UserProfile {
                    return UserProfile(userProfile.title, userProfile.phone, userProfile.skype, userProfile.realName, userProfile.realNameNormalized, userProfile.displayName, userProfile.displayNameNormalized, userProfile.fields, userProfile.statusText, userProfile.statusEmoji, userProfile.statusExpiration, userProfile.avatarHash, userProfile.alwaysActive, userProfile.imageOriginal, userProfile.email, userProfile.firstName, userProfile.lastName,
                            userProfile.image24, userProfile.image32, userProfile.image48, userProfile.image72, userProfile.image192, userProfile.image512, userProfile.image1024, userProfile.statusTextCanonical, userProfile.team)
                }
            }
        }

        companion object {
            /**
             * creates a [LocalUser] out of an [User]
             */
            fun of(user: User): LocalUser {
                return LocalUser(user.id, user.teamId, user.name, user.isDeleted, user.color, user.realName,
                        user.timezone, user.timezoneLabel, user.timezoneOffset, UserProfile.of(user.profile), user.isAdmin,
                        user.isOwner, user.isPrimaryOwner, user.isRestricted, user.isUltraRestricted, user.isBot,
                        user.lastModifiedAt, user.isAppUser, user.has2fa, user.locale)
            }
        }
    }

}
