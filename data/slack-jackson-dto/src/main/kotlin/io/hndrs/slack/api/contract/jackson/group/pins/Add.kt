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
    JsonSubTypes.Type(value = SuccessfulPinsAddResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorPinsAddResponse::class, name = "false")
)

@JacksonDataClass
sealed class PinsAddResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulPinsAddResponse(override val ok: Boolean) : PinsAddResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorPinsAddResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : PinsAddResponse(ok) {
    companion object
}

/**
 * This method pins a message to a particular conversation or channel.
 *
 * @property channel to pin the item in.
 * @property timestamp of the message to pin.
 */
data class PinsAddRequest(
    @JsonProperty("channel") val channel: String,
    @InstantToString @JsonProperty("timestamp") val timestamp: Instant
) {
    companion object
}
