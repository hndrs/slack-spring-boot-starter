package com.kreait.slack.api.contract.jackson.group.groups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGroupsCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGroupsCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class GroupsCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class SuccessfulGroupsCreateResponse(
        override val ok: Boolean,
        @JsonProperty("group") val group: Group) : GroupsCreateResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class ErrorGroupsCreateResponse constructor(
        override val ok: Boolean,
        @JsonProperty("error") val error: String
) : GroupsCreateResponse(ok) {
    companion object
}

/**
 * [SlackDoc](https://api.slack.com/methods/groups.Create)
 */
data class GroupsCreateRequest(@JsonProperty("name") val name: String,
                               @JsonProperty("validate") val validate: Boolean = true) {
    companion object
}

data class Group(
        @JsonProperty("created")
        val created: Int?,
        @JsonProperty("creator")
        val creator: String?,
        @JsonProperty("id")
        val id: String?,
        @JsonProperty("is_archived")
        val isArchived: Boolean,
        @JsonProperty("is_group")
        val isGroup: Boolean = true,
        @JsonProperty("is_open")
        val isOpen: Boolean,
        @JsonProperty("last_read")
        val lastRead: String?,
        @JsonProperty("latest")
        val latest: Any?,
        @JsonProperty("members")
        val members: List<String?>?,
        @JsonProperty("name")
        val name: String?,
        @JsonProperty("purpose")
        val purpose: Purpose?,
        @JsonProperty("topic")
        val topic: Topic?,
        @JsonProperty("unread_count")
        val unreadCount: Int?,
        @JsonProperty("unread_count_display")
        val unreadCountDisplay: Int?
) {
    companion object
}

data class Topic(
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("last_set") val lastSet: Int?,
        @JsonProperty("value") val value: String?
) {
    companion object
}

data class Purpose(
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("last_set") val lastSet: Int?,
        @JsonProperty("value") val value: String?
) {
    companion object
}