package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.JacksonDataClass

/**
 * https://api.slack.com/types/usergroup
 */
@JacksonDataClass
data class Usergroup(
        @JsonProperty("id") val id: String,
        @JsonProperty("team_id") val teamId: String,
        @JsonProperty("is_usergroup") val isUsergroup: Boolean,
        @JsonProperty("name") val name: String,
        @JsonProperty("description") val description: String,
        @JsonProperty("handle") val handle: String,
        @JsonProperty("is_external") val isExternal: Boolean,
        @JsonProperty("date_create") val dateCreate: Int,
        @JsonProperty("date_update") val dateUpdate: Int,
        @JsonProperty("date_delete") val dateDelete: Int,
        @JsonProperty("auto_type") val autoType: AutoType,
        @JsonProperty("created_by") val createdBy: String,
        @JsonProperty("updated_by") val updatedBy: String,
        @JsonProperty("deleted_by") val deletedBy: String,
        @JsonProperty("prefs") val prefs: Prefs,
        @JsonProperty("users") val users: List<String>,
        @JsonProperty("user_count") val userCount: Int
) {
    companion object
}

@JacksonDataClass
data class Prefs(
        @JsonProperty("channels") val channels: List<String>,
        @JsonProperty("groups") val groups: List<String>
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
