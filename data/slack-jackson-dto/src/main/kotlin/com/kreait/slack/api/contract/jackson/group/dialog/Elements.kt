package com.kreait.slack.api.contract.jackson.group.dialog

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


/**
 * https://api.slack.com/dialogs#text_elements
 */
@JacksonDataClass
data class TextElement constructor(@JsonProperty("label") override val label: String,
                                   @JsonProperty("name") override val name: String,
                                   @JsonProperty("type") override val type: Type,
                                   @JsonProperty("placeholder") val placeholder: String,
                                   @JsonProperty("value") val value: String = "",
                                   @JsonProperty("hint") val hint: String? = "") : Element(label, type, name)

@JsonSerialize(using = Subtype.Serializer::class)
enum class Subtype {
    EMAIL,
    NUMBER,
    TEL,
    URL,
    TEXT;

    class Serializer : JsonSerializer<Subtype>() {
        override fun serialize(value: Subtype?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }
}

@JsonSerialize(using = Type.Serializer::class)
enum class Type {
    TEXT,
    TEXTAREA,
    SELECT;

    class Serializer : JsonSerializer<Type>() {
        override fun serialize(value: Type?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }
}

/**
 * https://api.slack.com/dialogs#textarea_elements
 */
@JacksonDataClass
data class TextAreaElement constructor(@JsonProperty("label") override val label: String,
                                       @JsonProperty("name") override val name: String,
                                       @JsonProperty("type") override val type: Type,
                                       @JsonProperty("value") val value: String = "",
                                       @JsonProperty("hint") val hint: String = "") : Element(label, type, name)

/**
 * https://api.slack.com/dialogs#select_elements
 */
@JacksonDataClass
data class SelectElement constructor(@JsonProperty("label") override val label: String,
                                     @JsonProperty("name") override val name: String,
                                     @JsonProperty("type") override val type: Type,
                                     @JsonProperty("options") val options: List<Options>,
                                     @JsonProperty("hint") val hint: String? = "",
                                     @JsonProperty("value") val value: String = "") : Element(label, type, name)

@JacksonDataClass
data class Options constructor(@JsonProperty("label") val label: String,
                               @JsonProperty("value") val value: String)

@JacksonDataClass
data class DynamicSelectElement constructor(@JsonProperty("label") override val label: String,
                                            @JsonProperty("name") override val name: String,
                                            @JsonProperty("type") override val type: Type,
                                            @JsonProperty("data_source") val dataSource: DataSource) : Element(label, type, name)

@JsonSerialize(using = DataSource.Serializer::class)
enum class DataSource {
    USERS,
    CHANNELS,
    CONVERSATIONS,
    EXTERNAL;

    class Serializer : JsonSerializer<DataSource>() {
        override fun serialize(value: DataSource?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }
}
