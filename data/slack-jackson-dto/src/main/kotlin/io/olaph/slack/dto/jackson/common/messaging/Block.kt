package io.olaph.slack.dto.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.olaph.slack.dto.jackson.common.messaging.composition.Text

abstract class Block(@JsonProperty("type") val type: Type) {

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
}

class Divider : Block(Type.DIVIDER)

data class Section(@JsonProperty("text") val text: Text,
                   @JsonProperty("fields") val fields: List<Text>?,
                   @JsonProperty("block_id") val blockId: String,
                   @JsonProperty("accessory") val accessory: String) : Block(Type.SECTION) {

}

