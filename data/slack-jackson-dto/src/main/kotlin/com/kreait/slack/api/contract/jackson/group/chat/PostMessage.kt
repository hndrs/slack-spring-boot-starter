package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulPostMessageResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorPostMessageResponse::class, name = "false")
)
@JacksonDataClass
sealed class PostMessageResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the channel in which the message was posted
 * @property timestamp the timestamp of the posted message
 * @property message the posted message
 */
@JacksonDataClass
data class SuccessfulPostMessageResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("channel") val channel: String,
                                                     @InstantToString @JsonProperty("ts") val timestamp: Instant,
                                                     @JsonProperty("message") val message: Message? = null) : PostMessageResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorPostMessageResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String) : PostMessageResponse(ok) {
    companion object
}

/**
 * Posts a public Message to a Channel
 *
 * @property text the text of the message
 * @property channel the channel in which the message should be posted
 * @property attachments secondary attachments of the message
 * @property blocks secondary block items of the message
 * @property asUser Pass true to post the message as the authed user, instead of as a bot. Defaults to false.
 * @property username Set your bot's user name. Must be used in conjunction with as_user set to false, otherwise ignored.
 * @property iconEmoji Emoji to use as the icon for this message. Overrides icon_url. Must be used in conjunction with as_user set to false, otherwise ignored. See authorship below.
 * @property iconUrl URL to an image to use as the icon for this message. Must be used in conjunction with as_user set to false, otherwise ignored. See authorship below.
 * @property linkNames Find and link channel names and usernames.
 * @property markDown Disable Slack markup parsing by setting to false. Enabled by default.
 * @property parse the parse-mode
 * @property replyBroadcast Used in conjunction with thread_ts and indicates whether reply should be made visible to everyone in the channel or conversation. Defaults to false.
 * @property threadTimestamp Provide another message's ts value to make this message a reply. Avoid using a reply's ts value; use its parent instead.
 * @property unfurlLinks Pass true to enable unfurling of primarily text-based content.
 * @property unfurlMedia Pass false to disable unfurling of media content.
 *
 * @see [Slack Authorship guide](https://api.slack.com/methods/chat.postMessage#authorship)
 */
@JacksonDataClass
data class PostMessageRequest constructor(@JsonProperty("text") val text: String,
                                          @JsonProperty("channel") val channel: String,
                                          @JsonProperty("attachments") val attachments: List<Attachment>? = null,
                                          @JsonProperty("blocks") val blocks: List<Block>? = null,
                                          @JsonProperty("as_user") val asUser: Boolean? = null,
                                          @JsonProperty("username") val username: String? = null,
                                          @JsonProperty("icon_emoji") val iconEmoji: String? = null,
                                          @JsonProperty("icon_url") val iconUrl: String? = null,
                                          @JsonProperty("link_names") val linkNames: Boolean? = null,
                                          @JsonProperty("mrkdwn") val markDown: Boolean? = null,
                                          @JsonProperty("parse") val parse: ParseType? = null,
                                          @JsonProperty("reply_broadcast") val replyBroadcast: Boolean? = null,
                                          @InstantToString @JsonProperty("thread_ts") val threadTimestamp: Instant? = null,
                                          @JsonProperty("unfurl_links") val unfurlLinks: Boolean? = null,
                                          @JsonProperty("unfurl_media") val unfurlMedia: Boolean? = null) {
    companion object

}







