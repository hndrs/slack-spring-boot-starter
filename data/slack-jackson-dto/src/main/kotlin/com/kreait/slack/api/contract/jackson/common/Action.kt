package com.kreait.slack.api.contract.jackson.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

/**
 * An Action Item that can be used in Interactive Messages and Attachments
 *
 * @property actionId the id of this action
 * @property actionTimestamp the timestamp when the action was sent
 * @property blockId
 * @property name the name of this action
 * @property text the displayed text of this action
 * @property style the style
 * @property type The type of this action
 * @property value the value of this interaction
 * @property options the options for a select-action
 * @property selectedOptions the selected option
 */
@JacksonDataClass
data class Action(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("action_id") val actionId: String? = null,
    @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant? = null,
    @JsonProperty("block_id") val blockId: String? = null,
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("style") val style: Style? = null,
    @JsonProperty("type") val type: ActionType,
    @JsonProperty("value") val value: String? = null,
    @JsonProperty("options") val options: List<Option>? = listOf(),
    @JsonProperty("selected_options") val selectedOptions: List<Option>? = listOf()
) {

    @JsonDeserialize(using = ActionType.Deserializer::class)
    @JsonSerialize(using = ActionType.Serializer::class)
    enum class ActionType {
        BUTTON,
        SELECT;

        class Serializer : JsonSerializer<ActionType>() {
            override fun serialize(value: ActionType?, gen: JsonGenerator?, provider: SerializerProvider?) {
                gen?.writeString(value.toString().toLowerCase())
            }
        }

        class Deserializer : JsonDeserializer<ActionType>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): ActionType {
                return valueOf(p.text.toUpperCase())
            }
        }
    }

    @JsonDeserialize(using = Style.Deserializer::class)
    @JsonSerialize(using = Style.Serializer::class)
    enum class Style {
        DEFAULT,
        PRIMARY,
        DANGER;

        class Serializer : JsonSerializer<Style>() {
            override fun serialize(value: Style?, gen: JsonGenerator?, provider: SerializerProvider?) {
                gen?.writeString(value.toString().toLowerCase())
            }
        }

        class Deserializer : JsonDeserializer<Style>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Style {
                return Style.valueOf(p.text.toUpperCase())
            }
        }
    }

    @JacksonDataClass
    data class Option(
        @JsonProperty("text") val text: String? = null,
        @JsonProperty("value") val value: String
    )
}
