package com.kreait.slack.api.contract.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = Block.Divider::class, name = "divider"),
        JsonSubTypes.Type(value = Block.Action::class, name = "actions"),
        JsonSubTypes.Type(value = Block.Section::class, name = "section"),
        JsonSubTypes.Type(value = Block.Context::class, name = "context"),
        JsonSubTypes.Type(value = Block.Image::class, name = "image")
)
@JacksonDataClass
sealed class Block(@JsonProperty("type") val type: Type) {
    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(private val typeString: String) {
        DIVIDER("divider"),
        SECTION("section"),
        CONTEXT("context"),
        IMAGE("image"),
        ACTIONS("actions");

        class Serializer : JsonSerializer<Type>() {
            override fun serialize(value: Type, gen: JsonGenerator?, serializers: SerializerProvider?) {
                gen?.writeString(value.typeString)
            }
        }

        class Deserializer : JsonDeserializer<Type>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Type {
                return valueOf(p.text.toUpperCase())
            }

        }
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#divider
     */
    @JacksonDataClass
    class Divider : Block(Type.DIVIDER)

    /**
     * https://api.slack.com/reference/messaging/blocks#section
     */
    @JacksonDataClass
    data class Section(@JsonProperty("text") val text: Text,
                       @get:JsonProperty("block_id") val blockId: String,
                       @JsonProperty("fields") val fields: List<Text>? = null,
                       @JsonProperty("accessory") val accessory: Element? = null) : Block(Type.SECTION) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#image
     */
    @JacksonDataClass
    data class Image(@JsonProperty("image_url") val imageUrl: String,
                     @JsonProperty("alt_text") val altText: String,
                     @JsonProperty("title") val title: String? = null,
                     @get:JsonProperty("block_id") val blockId: String) : Block(Type.IMAGE) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#actions
     */
    @JacksonDataClass
    data class Action(@JsonProperty("elements") val elements: List<Element>,
                      @get:JsonProperty("block_id") val blockId: String) : Block(Type.ACTIONS) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#context
     * Displays message context, which can include both *images* and *text*.
     */
    @JacksonDataClass
    data class Context(@JsonProperty("elements") val elements: List<Any>,
                       @get:JsonProperty("block_id") val blockId: String) : Block(Type.CONTEXT) {
        companion object
    }
}