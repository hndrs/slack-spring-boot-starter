package io.hndrs.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.util.InstantToInt
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

/**
 * The Usergroup dto
 *
 * @property id the id of the usergroup
 * @property teamId the id of the according team
 * @property isUsergroup will be true
 * @property name the name of this usergroup
 * @property description the description of this usergroup
 * @property handle the handle of the usergroup, unique for all usergroups in that workspace
 * @property isExternal determines if the usergroup was created externally
 * @property dateCreate the date when the usergroup was created
 * @property dateUpdate the date when the usergroup was updated
 * @property dateDelete the date when the usergroup was deleted
 * @property autoType
 * @property createdBy the user-id of the user that created the usergroup
 * @property updatedBy the user-id of the user that updated the usergroup
 * @property deletedBy the user-id of the user that deleted the usergroup
 * @property prefs contains default channels and groups (private channels) that members of this group will be invited to upon joining.
 * @property users list of users in that usergroup
 * @property userCount the amount of users in that usergroup
 */
@JacksonDataClass
data class UserGroup(
    @JsonProperty("id") val id: String,
    @JsonProperty("team_id") val teamId: String,
    @JsonProperty("is_usergroup") val isUserGroup: Boolean,
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("handle") val handle: String,
    @JsonProperty("is_external") val isExternal: Boolean,
    @InstantToInt @JsonProperty("date_create") val createdAt: Instant,
    @InstantToInt @JsonProperty("date_update") val updatedAt: Instant,
    @InstantToInt @JsonProperty("date_delete") val deletedAt: Instant,
    @JsonProperty("auto_type") val autoType: AutoType,
    @JsonProperty("created_by") val createdBy: String,
    @JsonProperty("updated_by") val updatedBy: String,
    @JsonProperty("deleted_by") val deletedBy: String?,
    @JsonProperty("prefs") val prefs: Prefs,
    @JsonProperty("users") val userIds: List<String>,
    @JsonProperty("user_count") val userCount: Int
) {
    companion object
}

@JacksonDataClass
data class Prefs(
    @JsonProperty("channels") val channelIds: List<String>,
    @JsonProperty("groups") val groupIds: List<String>
) {
    companion object
}

/**
 * admins for a Workspace Admins group
 * owners for a Workspace Owners group
 * null for a custom group
 * see: https://api.slack.com/types/usergroup
 */
enum class AutoType(val type: String?) {
    ADMINS("admins"),
    OWNERS("owners"),
    NULL(null)
}
