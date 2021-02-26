package io.hndrs.slack.api.contract.jackson.group.stars

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.util.InstantToString
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulStarsRemoveResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorStarsRemoveResponse::class, name = "false")
)

@JacksonDataClass
sealed class StarsRemoveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulStarsRemoveResponse(override val ok: Boolean) : StarsRemoveResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorStarsRemoveResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : StarsRemoveResponse(ok) {
    companion object
}

/**
 * This method removes a star from an item (message, file, file comment, channel, private group, or DM)
 * on behalf of the authenticated user.
 * One of fileId, fileCommentId, channel, or the combination of channel and timestamp must be specified.
 *
 * @property channel to pin the item in.
 * @property fileId to un-pin. Optional.
 * @property fileCommentId to un-pin. Optional.
 * @property timestamp of the message to un-pin. Optional.
 */
data class StarsRemoveRequest(
    @JsonProperty("channel") val channel: String? = null,
    @JsonProperty("file") val fileId: String? = null,
    @JsonProperty("file_comment") val fileCommentId: String? = null,
    @InstantToString @JsonProperty("timestamp") val timestamp: Instant? = null
) {
    companion object
}
