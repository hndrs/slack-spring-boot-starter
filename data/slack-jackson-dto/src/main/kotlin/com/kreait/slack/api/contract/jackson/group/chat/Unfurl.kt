package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChatUnfurlResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChatUnfurlResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatUnfurlResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChatUnfurlResponse constructor(override val ok: Boolean)
    : ChatUnfurlResponse(ok) {
    companion object
}


@JacksonDataClass
data class ErrorChatUnfurlResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : ChatUnfurlResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChatUnfurlRequest constructor(@JsonProperty("channel") val channelId: String,
                                         @InstantToString @JsonProperty("ts") val timestamp: Instant,
                                         @JsonProperty("unfurls") val unfurls: Map<String, String>,
                                         @JsonProperty("user_auth_message") val userAuthMessage: String? = null,
                                         @JsonProperty("user_auth_required") val userAuthRequired: Boolean = false,
                                         @JsonProperty("user_auth_url") val userAuthUrl: String? = null
) {
    companion object
}
