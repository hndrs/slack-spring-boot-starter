package io.hndrs.slack.api.contract.jackson.group.oauth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.common.types.Team
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass


data class AccessRequest(val clientId: String, val client_secret: String, val code: String) {
    companion object
}


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfullAccessResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorAccessResponse::class, name = "false")
)
@JacksonDataClass
sealed class AccessResponse(@JsonProperty("ok") open val ok: Boolean)


@JacksonDataClass
data class ErrorAccessResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : AccessResponse(ok) {
    companion object
}

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property accessToken the accesstoken of the installed app (if the app is a bot user, the token will be in the bot property)
 * @property scope the requested scopes
 * @property userId the userId of the installing user
 * @property teamName the team name where the app was installed
 * @property teamId the team id of the theam where the app was installed
 * @property incomingWebhook the webhook which is used to post messages to
 * @property bot the bot object with the according information
 */
data class SuccessfullAccessResponse(
    @JsonProperty("ok") override val ok: Boolean,
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("scope") val scope: String,
    @JsonProperty("bot_user_id") val botUserId: String,
    @JsonProperty("app_id") val appId: String,
    @JsonProperty("team") val team: Team,
    @JsonProperty("enterprise") val enterprise: Enterprise?
) : AccessResponse(ok) {
    companion object {}

    data class Enterprise(
        @JsonProperty("name") val name: String?,
        @JsonProperty("id") val id: String
    ) {
        companion object
    }

    data class AuthedUser(
        @JsonProperty("id") val id: String,
        @JsonProperty("scope") val scope: String,
        @JsonProperty("accessToken") val accessToken: String,
        @JsonProperty("tokenType") val tokenType: String
    ) {
        companion object
    }
}

