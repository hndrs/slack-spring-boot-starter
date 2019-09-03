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
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Confirmation
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Option
import com.kreait.slack.api.contract.jackson.common.messaging.composition.OptionGroup
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.LocalDate

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = Element.Image::class, name = "image"),
        JsonSubTypes.Type(value = Element.Button::class, name = "button"),
        JsonSubTypes.Type(value = Element.StaticSelect::class, name = "static_select"),
        JsonSubTypes.Type(value = Element.ExternalSelect::class, name = "external_select"),
        JsonSubTypes.Type(value = Element.UsersSelect::class, name = "users_select"),
        JsonSubTypes.Type(value = Element.ConversationsSelect::class, name = "conversations_select"),
        JsonSubTypes.Type(value = Element.ChannelsSelect::class, name = "channels_select"),
        JsonSubTypes.Type(value = Element.DatePicker::class, name = "datepicker"),
        JsonSubTypes.Type(value = Element.Overflow::class, name = "overflow")
)
@JacksonDataClass
sealed class Element(@JsonProperty("type") open val type: Type) {

    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(internal val typeString: String) {
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
                gen?.writeString(value.typeString.toLowerCase())
            }
        }

        class Deserializer : JsonDeserializer<Type>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Type {
                return valueOf(p.text.toUpperCase())
            }
        }
    }


    /**
     * https://api.slack.com/reference/messaging/block-elements#image
     */
    @JacksonDataClass
    data class Image constructor(@JsonProperty("image_url") val imageUrl: String,
                                 @JsonProperty("alt_text") val altText: String) : Element(Type.IMAGE) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#button
     */
    @JacksonDataClass
    data class Button constructor(@JsonProperty("text") val text: Text,
                                  @JsonProperty("action_id") val actionId: String,
                                  @JsonProperty("url") val url: String? = null,
                                  @JsonProperty("value") val value: String? = null,
                                  @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.BUTTON) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#select
     */
    @JacksonDataClass
    data class StaticSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                        @JsonProperty("action_id") val actionId: String,
                                        @JsonProperty("options") val options: List<Option>,
                                        @JsonProperty("option_groups") val optionGroups: List<OptionGroup>? = null,
                                        @JsonProperty("initial_option") val initialOption: Option? = null,
                                        @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.STATIC_SELECT) {
        companion object
    }


    /**
     * https://api.slack.com/reference/messaging/block-elements#external-select
     */
    @JacksonDataClass
    data class ExternalSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                          @JsonProperty("action_id") val actionId: String,
                                          @JsonProperty("options") val options: List<Option>,
                                          @JsonProperty("option_groups") val optionGroups: List<OptionGroup>? = null,
                                          @JsonProperty("initial_option") val initialOption: Option? = null,
                                          @JsonProperty("min_query_length") val minQueryLength: Int? = null,
                                          @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.EXTERNAL_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#users-select
     */
    @JacksonDataClass
    data class UsersSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                       @JsonProperty("action_id") val actionId: String,
                                       @JsonProperty("initial_user") val initialUserId: String? = null,
                                       @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.USERS_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#conversation-select
     */
    @JacksonDataClass
    data class ConversationsSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                               @JsonProperty("action_id") val actionId: String,
                                               @JsonProperty("initial_conversation") val initialConversationId: String? = null,
                                               @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.CONVERSATIONS_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#channel-select
     */
    @JacksonDataClass
    data class ChannelsSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                          @JsonProperty("action_id") val actionId: String,
                                          @JsonProperty("initial_channels") val initialChannelsId: String? = null,
                                          @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.CHANNELS_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#overflow
     */
    @JacksonDataClass
    data class Overflow constructor(@JsonProperty("action_id") val actionId: String,
                                    @JsonProperty("options") val options: List<Option>,
                                    @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.OVERFLOW) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#datepicker
     */
    @JacksonDataClass
    data class DatePicker constructor(@JsonProperty("action_id") val actionId: String,
                                      @JsonProperty("placeholder") val placeholderText: Text? = null,
                                      @JsonProperty("initial_date") val initialChannelsId: LocalDate? = null,
                                      @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.OVERFLOW) {
        companion object
    }
}
