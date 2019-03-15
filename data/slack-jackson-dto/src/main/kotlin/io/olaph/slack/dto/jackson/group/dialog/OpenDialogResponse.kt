package io.olaph.slack.dto.jackson.group.dialog

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.group.chat.Action


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulOpenDialogResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorOpenDialogResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackOpenDialogResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulOpenDialogResponse constructor(override val ok: Boolean)
    : SlackOpenDialogResponse(ok){
    companion object
}

@JacksonDataClass
data class ErrorOpenDialogResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String,
                                               @JsonProperty("response_metadata") val metadata: MetaData?
) : SlackOpenDialogResponse(ok)

@JacksonDataClass
data class MetaData constructor(@JsonProperty("messages") val messages: List<String>?)

@JacksonDataClass
data class SlackOpenDialogRequest constructor(@JsonProperty("dialog") val dialog: Dialog,
                                              @JsonProperty("trigger_id") val trigger_id: String) {
    companion object

}

@JacksonDataClass
data class Dialog constructor(@JsonProperty("callback_id") val callback_id: String,
                              @JsonProperty("title") val title: String,
                              @JsonProperty("state") val state: String? = "",
                              @JsonProperty("elements") val elements: List<Element>) {
    companion object
}

@JacksonDataClass
abstract class Element(@JsonProperty("label") open val label: String,
                       @JsonProperty("type") open val type: Type,
                       @JsonProperty("name") open val name: String)

@JacksonDataClass
@JsonIgnoreProperties(ignoreUnknown = true)
data class InteractiveComponentResponse(
        @JsonProperty("type") val type: String?,
        @JsonProperty("token") val token: String?,
        //TODO can this really be optional?
        @JsonProperty("action_ts") val actionTs: String?,
        //TODO can this really be optional?
        @JsonProperty("message_ts") val messageTs: String?,
        //TODO can this really be optional?
        @JsonProperty("team") val team: Team?,
        @JsonProperty("user") val user: User,
        @JsonProperty("state") val state: String? = "",
        @JsonProperty("channel") val channel: Channel,
        //TODO can this really be optional?
        @JsonProperty("submission") val submission: Map<String, Any>?,
        @JsonProperty("callback_id") val callbackId: String?,
        @JsonProperty("response_url") val responseUrl: String?,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("trigger_id") val triggerId: String?)

@JacksonDataClass
data class Channel(@JsonProperty("id") val id: String,
                   @JsonProperty("name") val name: String)

@JacksonDataClass
data class User(@JsonProperty("id") val id: String,
                @JsonProperty("name") val name: String,
                @JsonProperty("team_id") val teamId: String?)

@JacksonDataClass
data class Team(@JsonProperty("id") val id: String,
                @JsonProperty("domain") val domain: String,
                @JsonProperty("enterprise_id") val enterpriseId: String?,
                @JsonProperty("enterprise_name") val enterpriseName: String?)
