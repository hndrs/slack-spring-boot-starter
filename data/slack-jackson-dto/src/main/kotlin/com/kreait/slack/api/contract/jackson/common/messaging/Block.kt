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

/**
 * Used to determine the Type of the Block Element.
 *
 * @property type Specifies the type of the Block you want to use.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true
)
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
     * A Block of the Type 'divider'.
     * Dividers are used to split content. Imagine using the <hr>-Tag in HTML - this is conceptual the same.
     *
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/blocks#divider)
     */
    @JacksonDataClass
    class Divider : Block(Type.DIVIDER)

    /**
     * A Block of the Type 'section'.
     *
     * States as 'the most flexible blocks available' this can be used to display text as is, in combination with fields
     * or alongside the other available block elements.
     *
     * @property text The text for the block. (Max length 3000 chars).
     * @property blockId Generated if not specified. Used to identify the block's origin when using interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property fields List of [Text] objects. Will be displayed in a compact way in order to use side-by-side text views with a maximum number of 10. (Max length of each item is 2000 chars).
     * @property accessory Can hold one [Element] object.
     *
     * @see [Text]
     * @see [Element]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/blocks#section)
     */
    @JacksonDataClass
    data class Section(
        @JsonProperty("text") val text: Text,
        @JsonProperty("block_id") val blockId: String? = null,
        @JsonProperty("fields") val fields: List<Text>? = null,
        @JsonProperty("accessory") val accessory: Element? = null
    ) : Block(Type.SECTION) {
        companion object
    }

    /**
     * A Block of the Type 'image'.
     *
     * @property imageUrl The URL of the image displayed. (Max length 3000 chars).
     * @property altText A plain text describing the image.
     * @property title An optional title for the image.
     * @property blockId Generated if not specified. Used to identify the block's origin when using interactions. Should be unique, even on iterations. (Max length 255 chars).
     *
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/blocks#image)
     */
    @JacksonDataClass
    data class Image(
        @JsonProperty("image_url") val imageUrl: String,
        @JsonProperty("alt_text") val altText: String,
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("block_id") val blockId: String? = null
    ) : Block(Type.IMAGE) {
        companion object
    }

    /**
     * A Block of the Type 'actions' that can hold interactive elements.
     *
     * @property elements A List of [Element] objects.
     * @property blockId Generated if not specified. Used to identify the block's origin when using interactions. Should be unique, even on iterations. (Max length 255 chars).
     *
     * @see [Element]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/blocks#actions)
     */
    @JacksonDataClass
    data class Action(
        @JsonProperty("elements") val elements: List<Element>,
        @JsonProperty("block_id") val blockId: String? = null
    ) : Block(Type.ACTIONS) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#context
     * Displays message context, which can include both *images* and *text*.
     * A Block of the Type 'context' wich can hold text and/or images.
     *
     * @property elements A List of [Element] objects.
     * @property blockId Generated if not specified. Used to identify the block's origin when using interactions. Should be unique, even on iterations. (Max length 255 chars).
     *
     * @see [Element]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/blocks#context)
     */
    @JacksonDataClass
    data class Context(
        @JsonProperty("elements") val elements: List<Any>,
        @JsonProperty("block_id") val blockId: String? = null
    ) : Block(Type.CONTEXT) {
        companion object
    }
}
