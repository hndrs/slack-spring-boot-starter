package com.kreait.slack.api.contract.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)

@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChannelsSetPurposeResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChannelsSetPurposeResponse::class, name = "false")
)

@JacksonDataClass
sealed class ChannelsSetPurposeResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChannelsSetPurposeResponse constructor(override val ok: Boolean,
                                                            @JsonProperty("purpose") val purpose: String)
    : ChannelsSetPurposeResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorChannelsSetPurposeResponse constructor(override val ok: Boolean,
                                                       @JsonProperty("error") val error: String)
    : Channelsclient/slack-spring-test-api-client/src/main/kotlin/com/kreait/slack/api/test/group/channel/MockChannelsMethodGroup.ktSetPurposeResponse(ok) {
    companion object
}


@JacksonDataClass
data class ChannelsSetPurposeRequest constructor(@JsonProperty("channel") val channel: String,
                                                 @JsonProperty("purpose") val purpose: String,
                                                 @JsonProperty("name_tagging") val nameTagging: Boolean? = true) {

    companion object
}
