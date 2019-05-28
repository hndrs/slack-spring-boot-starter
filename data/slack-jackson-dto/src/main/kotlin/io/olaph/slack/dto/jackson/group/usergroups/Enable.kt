package io.olaph.slack.dto.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Usergroup

/**
 * https://api.slack.com/methods/usergroups.enable
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsEnableResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsEnableResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsEnableResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupsEnableResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("usergroup") val usergroup: Usergroup)
    : UsergroupsEnableResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupsEnableResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : UsergroupsEnableResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackUsergroupsEnableRequest constructor(@JsonProperty("usergroup") val usergroupId: String,
                                                    @JsonProperty("include_count") val includeCount: Boolean?) {
    companion object
}