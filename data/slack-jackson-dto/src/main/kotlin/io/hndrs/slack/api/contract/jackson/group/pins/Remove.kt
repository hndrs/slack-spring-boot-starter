package io.hndrs.slack.api.contract.jackson.group.pins

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
    JsonSubTypes.Type(value = SuccessfulPinsRemoveResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorPinsRemoveResponse::class, name = "false")
)

@JacksonDataClass
sealed class PinsRemoveResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulPinsRemoveResponse(override val ok: Boolean) : PinsRemoveResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorPinsRemoveResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : PinsRemoveResponse(ok) {
    companion object
}

/**
 * This method pins a message to a particular conversation or channel.
 *
 * @property channel to pin the item in.
 * @property fileId to un-pin. Optional.
 * @property fileCommentId to un-pin. Optional.
 * @property messageTimestamp of the message to un-pin. Optional.
 */
data class PinsRemoveRequest(
    @JsonProperty("channel") val channel: String,
    @JsonProperty("file") val fileId: String? = null,
    @JsonProperty("file_comment") val fileCommentId: String? = null,
    @InstantToString @JsonProperty("timestamp") val messageTimestamp: Instant? = null
) {
    companion object
}
