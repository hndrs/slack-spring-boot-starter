package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Channel


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationsInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationsInfoResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulConversationsInfoResponse constructor(override val ok: Boolean,
                                                           @JsonProperty("channel") val channel: Channel)
    : ConversationsInfoResponse(ok) {

    companion object
}

@JacksonDataClass
data class ErrorConversationsInfoResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("error") val error: String)
    : ConversationsInfoResponse(ok) {

    companion object
}

@JacksonDataClass
data class ConversationsInfoRequest constructor(@JsonProperty("channel") val channel: String,
                                                @JsonProperty("include_locale") val includeLocale: Boolean = false,
                                                @JsonProperty("include_num_members") val includeNumMembers: Boolean = false) {
    companion object

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf("channel" to channel)
        requestMap["include_locale"] = includeLocale.toString()
        requestMap["include_num_members"] = includeNumMembers.toString()
        return requestMap
    }
}