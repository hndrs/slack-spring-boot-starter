package com.kreait.slack.api.contract.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = Divider::class, name = "divider"),
        JsonSubTypes.Type(value = Action::class, name = "actions"),
        JsonSubTypes.Type(value = Section::class, name = "section"),
        JsonSubTypes.Type(value = Context::class, name = "context"),
        JsonSubTypes.Type(value = Image::class, name = "image")
)
@JacksonDataClass
sealed class Block(@JsonProperty("type") open val type: String) {
}

/**
 * https://api.slack.com/reference/messaging/blocks#divider
 */
@JacksonDataClass
class Divider(override val type: String) : Block(type)

/**
 * https://api.slack.com/reference/messaging/blocks#section
 */

@JacksonDataClass
data class Section(@JsonProperty("type") override val type: String = "section",
                   @JsonProperty("text") val text: Text,
                   @JsonProperty("block_id") val blockId: String? = null,
                   @JsonProperty("fields") val fields: List<Text>? = null,
                   @JsonProperty("accessory") val accessory: Element? = null) : Block(type) {
    companion object
}

/**
 * https://api.slack.com/reference/messaging/blocks#image
 */
@JacksonDataClass
data class Image(override val type: String = "image",
                 @JsonProperty("image_url") val imageUrl: String,
                 @JsonProperty("alt_text") val altText: String,
                 @JsonProperty("title") val title: String? = null,
                 @JsonProperty("block_id") val blockId: String? = null) : Block(type) {
    companion object
}

/**
 * https://api.slack.com/reference/messaging/blocks#actions
 */
@JacksonDataClass
data class Action(@JsonProperty("type") override val type: String = "actions",
                  @JsonProperty("elements") val elements: List<Element>,
                  @JsonProperty("block_id") val blockId: String? = null) : Block(type) {
    companion object
}

/**
 * https://api.slack.com/reference/messaging/blocks#context
 */
@JacksonDataClass
data class Context(override val type: String = "context",
                   @JsonProperty("elements") val elements: List<Element>,
                   @JsonProperty("block_id") val blockId: String? = null) : Block(type) {
    companion object
}



