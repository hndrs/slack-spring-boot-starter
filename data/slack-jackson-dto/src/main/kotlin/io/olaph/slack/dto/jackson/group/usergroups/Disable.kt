package io.olaph.slack.dto.jackson.group.usergroups

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

/**
 * https://api.slack.com/methods/usergroups.disable
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsDisableResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsDisableResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsDisableResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupsDisableResponse constructor(override val ok: Boolean,
                                                           @JsonProperty("usergroup") val usergroup: Usergroup)
    : UsergroupsDisableResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupsDisableResponse constructor(override val ok: Boolean,
                                                      @JsonProperty("error") val error: String)
    : UsergroupsDisableResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackUsergroupsDisableRequest constructor(@JsonProperty("usergroup") val usergroup: Usergroup,
                                                     @JsonProperty("inclue_count") val includeCount: Boolean?) {
    companion object
}

