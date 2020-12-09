package com.kreait.slack.api.contract.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.list
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulListResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorListResponse::class, name = "false")
)

@JacksonDataClass
sealed class ListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("usergroups") val userGroups: List<UserGroup>
) : ListResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ListResponse(ok) {
    companion object
}

/**
 * List all User Groups for a team
 *
 * @property includeCount Include the number of users in each User Group.
 * @property includeDisabled Include disabled User Groups.
 * @property includeUsers Include the list of users for each User Group.
 */
data class ListRequest(
    private val includeCount: Boolean?,
    private val includeDisabled: Boolean?,
    private val includeUsers: Boolean?
) {
    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        includeCount?.let { requestMap.put("include_count", it.toString()) }
        includeDisabled?.let { requestMap.put("include_disabled", it.toString()) }
        includeUsers?.let { requestMap.put("include_users", it.toString()) }
        return requestMap
    }
}
