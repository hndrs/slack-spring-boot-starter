package com.kreait.slack.api.contract.jackson.group.dialog

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

//TODO: Refactor names, e.g. should be DialogOpenResponse instead of SlackOpenDialogResponse
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulOpenDialogResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorOpenDialogResponse::class, name = "false")
)
@JacksonDataClass
sealed class SlackOpenDialogResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulOpenDialogResponse constructor(override val ok: Boolean)
    : SlackOpenDialogResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 * @property metadata additional information about the error
 */
@JacksonDataClass
data class ErrorOpenDialogResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String,
                                               @JsonProperty("response_metadata") val metadata: MetaData? = null) : SlackOpenDialogResponse(ok) {
    companion object
}

@JacksonDataClass
data class MetaData constructor(@JsonProperty("messages") val messages: List<String>?)

/**
 * Open a dialog with a user
 *
 * @property dialog the dialog you want to open
 * @property trigger_id the triggerId you receive by invoking a slashcommand or interactive component
 */
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

