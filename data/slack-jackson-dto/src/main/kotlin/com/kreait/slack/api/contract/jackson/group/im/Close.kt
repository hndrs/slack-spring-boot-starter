package com.kreait.slack.api.contract.jackson.group.im

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)

@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulImCloseResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorImCloseResponse::class, name = "false")
)

@JacksonDataClass
sealed class ImCloseResponse constructor(@JsonProperty(value = "ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property noOp  true if the Im-Channel is already closed
 * @property alreadyClosed true if the Im-Channel is already closed
 */
@JacksonDataClass
data class SuccessfulImCloseResponse constructor(
    override val ok: Boolean,
    @JsonProperty(value = "no_op") val noOp: Boolean?,
    @JsonProperty(value = "already_closed") val alreadyClosed: Boolean?
) : ImCloseResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorImCloseResponse constructor(
    override val ok: Boolean,
    @JsonProperty(value = "error") val error: String
) : ImCloseResponse(ok) {
    companion object
}

/**
 * Closes a im-channel
 *
 * @property channelId the channel of the im-channel you want to close
 */
@JacksonDataClass
data class ImCloseRequest constructor(@JsonProperty(value = "channel") val channelId: String) {
    companion object
}
