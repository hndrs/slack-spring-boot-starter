package io.hndrs.slack.api.contract.jackson.group.auth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulAuthTestResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorAuthTestResponse::class, name = "false")
)
@JacksonDataClass
sealed class AuthTestResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property url url to the slack-workspace
 * @property team the team-name in which the requesting user is
 * @property user the username of the requesting user
 * @property teamId the teamId in which the requesting user is
 * @property userId the user-id of the requesting user
 */
@JacksonDataClass
data class SuccessfulAuthTestResponse constructor(
    override val ok: Boolean,
    @JsonProperty("url") val url: String,
    @JsonProperty("team") val team: String,
    @JsonProperty("user") val user: String,
    @JsonProperty("team_id") val teamId: String,
    @JsonProperty("user_id") val userId: String
) : AuthTestResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 * @see [Slack Api Method](https://api.slack.com/methods/auth.test)
 */
@JacksonDataClass
data class ErrorAuthTestResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : AuthTestResponse(ok) {
    companion object
}
