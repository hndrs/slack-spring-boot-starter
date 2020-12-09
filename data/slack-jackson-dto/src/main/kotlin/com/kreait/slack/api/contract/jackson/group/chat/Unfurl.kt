package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulChatUnfurlResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorChatUnfurlResponse::class, name = "false")
)
@JacksonDataClass
sealed class ChatUnfurlResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulChatUnfurlResponse constructor(override val ok: Boolean) : ChatUnfurlResponse(ok) {
    companion object
}


/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorChatUnfurlResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ChatUnfurlResponse(ok) {
    companion object
}

/**
 * This method attaches Slack app unfurl behavior to a specified and relevant message. A user token is required as this method does not support bot user tokens.
 *
 * @property channelId Channel ID of the message
 * @property timestamp Timestamp of the message to add unfurl behavior to.
 * @property unfurls URL-encoded JSON map with keys set to URLs featured in the the message, pointing to their unfurl blocks or message attachments.
 * @property userAuthMessage Provide a simply-formatted string to send as an ephemeral message to the user as invitation to authenticate further and enable full unfurling behavior
 * @property userAuthRequired Set to true or 1 to indicate the user must install your Slack app to trigger unfurls for this domain
 * @property userAuthUrl Send users to this custom URL where they will complete authentication in your app to fully trigger unfurling. Value should be properly URL-encoded.
 */
@JacksonDataClass
data class ChatUnfurlRequest constructor(
    @JsonProperty("channel") val channelId: String,
    @InstantToString @JsonProperty("ts") val timestamp: Instant,
    @JsonProperty("unfurls") val unfurls: Map<String, String>,
    @JsonProperty("user_auth_message") val userAuthMessage: String? = null,
    @JsonProperty("user_auth_required") val userAuthRequired: Boolean = false,
    @JsonProperty("user_auth_url") val userAuthUrl: String? = null
) {
    companion object
}
