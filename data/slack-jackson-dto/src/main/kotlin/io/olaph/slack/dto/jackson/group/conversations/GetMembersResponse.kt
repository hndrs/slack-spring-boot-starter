package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGetMembersResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetMembersResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackGetMembersResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulGetMembersResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("members") val members: List<String>,
                                                    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata)
    : SlackGetMembersResponse(ok)

@JacksonDataClass
data class ErrorGetMembersResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : SlackGetMembersResponse(ok)

data class ResponseMetadata(@JsonProperty("next_cursor") val nextCursor: String)

