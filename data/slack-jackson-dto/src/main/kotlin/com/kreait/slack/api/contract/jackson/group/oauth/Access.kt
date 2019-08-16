package com.kreait.slack.api.contract.jackson.group.oauth

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


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
data class ErrorAccessResponse constructor(override val ok: Boolean,
                                           @JsonProperty("error") val error: String)
    : AccessResponse(ok) {
    companion object
}

data class SuccessfullAccessResponse(
        @JsonProperty("ok") override val ok: Boolean,
        @JsonProperty("access_token") val accessToken: String,
        @JsonProperty("scope") val scope: String,
        @JsonProperty("user_id") val userId: String,
        @JsonProperty("team_name") val teamName: String,
        @JsonProperty("team_id") val teamId: String,
        @JsonProperty("incoming_webhook") val incomingWebhook: IncomingWebhook,
        @JsonProperty("bot") val bot: Bot
) : AccessResponse(ok) {
    companion object
}

data class Bot(
        @JsonProperty("bot_user_id") val botUserId: String,
        @JsonProperty("bot_access_token") val botAccessToken: String
) {
    companion object
}

data class IncomingWebhook(
        @JsonProperty("channel") val channel: String,
        @JsonProperty("channel_id") val channelId: String,
        @JsonProperty("configuration_url") val configurationUrl: String,
        @JsonProperty("url") val url: String
) {
    companion object
}

