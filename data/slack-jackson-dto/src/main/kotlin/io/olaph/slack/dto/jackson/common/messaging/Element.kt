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
import java.time.LocalDate

sealed class Element(@JsonProperty("type") val type: Type) {

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


    /**
     * https://api.slack.com/reference/messaging/block-elements#image
     */
    data class Image constructor(@JsonProperty("image_url") val imageUrl: String,
                                 @JsonProperty("alt_text") val altText: String) : Element(Type.IMAGE)

    /**
     * https://api.slack.com/reference/messaging/block-elements#button
     */
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
    data class UsersSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                       @JsonProperty("action_id") val actionId: String,
                                       @JsonProperty("initial_user") val initialUserId: String? = null,
                                       @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.USERS_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#conversation-select
     */
    data class ConversationsSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                               @JsonProperty("action_id") val actionId: String,
                                               @JsonProperty("initial_conversation") val initialConversationId: String? = null,
                                               @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.CONVERSATIONS_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#channel-select
     */
    data class ChannelsSelect constructor(@JsonProperty("placeholder") val placeholderText: Text,
                                          @JsonProperty("action_id") val actionId: String,
                                          @JsonProperty("initial_channels") val initialChannelsId: String? = null,
                                          @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.CHANNELS_SELECT) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#overflow
     */
    data class Overflow constructor(@JsonProperty("action_id") val actionId: String,
                                    @JsonProperty("options") val options: List<Option>,
                                    @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.OVERFLOW) {
        companion object
    }

    /**
     * https://api.slack.com/reference/messaging/block-elements#datepicker
     */
    data class DatePicker constructor(@JsonProperty("action_id") val actionId: String,
                                      @JsonProperty("placeholder") val placeholderText: Text? = null,
                                      @JsonProperty("initial_date") val initialChannelsId: LocalDate? = null,
                                      @JsonProperty("confirm") val confirmation: Confirmation? = null) : Element(Type.OVERFLOW) {
        companion object
    }
}
