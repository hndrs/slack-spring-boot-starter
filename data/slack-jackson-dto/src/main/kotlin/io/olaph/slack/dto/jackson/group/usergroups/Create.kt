package io.olaph.slack.dto.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Channel
import io.olaph.slack.dto.jackson.common.types.Usergroup

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsCreateResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsCreateResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupsCreateResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("usergroup") val usergroup: Usergroup)
    : UsergroupsCreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupsCreateResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : UsergroupsCreateResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackUsergroupsCreateRequest constructor(@JsonProperty("name") val name: String,
                                                    @JsonProperty("channels") val channels: List<Channel>?,
                                                    @JsonProperty("description") val description: String?,
                                                    @JsonProperty("handle") val handle: String?,
                                                    @JsonProperty("include_count") val includeCount: Boolean?) {
    companion object
}