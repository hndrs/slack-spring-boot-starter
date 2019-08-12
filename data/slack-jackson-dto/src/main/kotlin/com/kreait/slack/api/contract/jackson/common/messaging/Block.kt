package com.kreait.slack.api.contract.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text

sealed class Block(@JsonProperty("type") val type: Type) {

    @JsonSerialize(using = Type.Serializer::class)
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
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#divider
     */
    class Divider : Block(Type.DIVIDER)

    /**
     * https://api.slack.com/reference/messaging/blocks#section
     */
    data class Section(@JsonProperty("text") val text: Text,
                       @JsonProperty("block_id") val blockId: String? = null,
                       @JsonProperty("fields") val fields: List<Text>? = null,
                       @JsonProperty("accessory") val accessory: Element? = null) : Block(Type.SECTION) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#image
     */
    data class Image(@JsonProperty("image_url") val imageUrl: String,
                     @JsonProperty("alt_text") val altText: String,
                     @JsonProperty("title") val title: String? = null,
                     @JsonProperty("block_id") val blockId: String? = null) : Block(Type.IMAGE) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#actions
     */
    data class Action(@JsonProperty("elements") val elements: List<Element>,
                      @JsonProperty("block_id") val blockId: String? = null) : Block(Type.ACTIONS) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/blocks#context
     */
    data class Context(@JsonProperty("elements") val elements: List<Element>,
                       @JsonProperty("block_id") val blockId: String? = null) : Block(Type.ACTIONS) {
        companion object
    }
}


