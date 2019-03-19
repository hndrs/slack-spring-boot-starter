package io.olaph.slack.dto.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.olaph.slack.dto.jackson.common.messaging.composition.Confirmation
import io.olaph.slack.dto.jackson.common.messaging.composition.Option
import io.olaph.slack.dto.jackson.common.messaging.composition.OptionGroup
import io.olaph.slack.dto.jackson.common.messaging.composition.Text

abstract class Element(@JsonProperty("type") val type: Type) {

    @JsonSerialize(using = Type.Serializer::class)
    enum class Type(private val typeString: String) {
        IMAGE("image"),
        BUTTON("button"),
        STATIC_SELECT("static_select"),
        EXTERNAL_SELECT("external_select"),
        USERS_SELECT("users_select"),
        CONVERSATIONS_SELECT("conversations_select"),
        CHANNELS_SELECT("channels_select"),
        DATE_PICKER("datepicker"),
        OVERFLOW("overflow");

        class Serializer : JsonSerializer<Type>() {
            override fun serialize(value: Type, gen: JsonGenerator?, serializers: SerializerProvider?) {
                gen?.writeString(value.typeString)
            }
        }
    }
}

/**
 * https://api.slack.com/reference/messaging/block-elements#image
 */
data class ImageElement constructor(@JsonProperty("image_url") val imageUrl: String,
                                    @JsonProperty("alt_text") val altText: String) : Element(Type.IMAGE)

/**
 * https://api.slack.com/reference/messaging/block-elements#button
 */
data class ButtonElement constructor(@JsonProperty("text") val text: Text,
                                     @JsonProperty("action_id") val actionId: String,
                                     @JsonProperty("url") val url: String?,
                                     @JsonProperty("value") val value: String?,
                                     @JsonProperty("confirm") val confirmation: Confirmation?) : Element(Type.BUTTON)

/**
 * https://api.slack.com/reference/messaging/block-elements#select
 */
data class StaticelectElement constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                          @JsonProperty("action_id") val actionId: String,
                                          @JsonProperty("options") val options: List<Option>,
                                          @JsonProperty("option_groups") val optionGroups: List<OptionGroup>?,
                                          @JsonProperty("initial_option") val initialOption: Option?,
                                          @JsonProperty("confirm") val confirmation: Confirmation?) : Element(Type.STATIC_SELECT)
