package com.kreait.slack.api.contract.jackson.group.stars

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
    JsonSubTypes.Type(value = SuccessfulStarsAddResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorStarsAddResponse::class, name = "false")
)

@JacksonDataClass
sealed class StarsAddResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulStarsAddResponse(override val ok: Boolean) : StarsAddResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorStarsAddResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : StarsAddResponse(ok) {
    companion object
}

/**
 * This method adds a star to an item (message, file, file comment, channel, private group, or DM)
 * on behalf of the authenticated user.
 * One of fileId, fileCommentId, channel, or the combination of channel and timestamp must be specified.
 *
 * @property channel to add star to, or channel where the message to add star to was posted (used with timestamp).
 * @property fileId to add star to.
 * @property fileCommentId to add star to.
 * @property timestamp of the message to add star to.
 */
data class StarsAddRequest(
    @JsonProperty("channel") val channel: String? = null,
    @JsonProperty("file") val fileId: String? = null,
    @JsonProperty("file_comment") val fileCommentId: String? = null,
    @InstantToString @JsonProperty("timestamp") val timestamp: Instant? = null
) {
    companion object
}
