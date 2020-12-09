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
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant
import java.time.LocalDate

/**
 * Used to determine the Type of the Element.
 * @property blockId the id of the according block
 * @property type Specifies the type of the Element you want to use.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Element.Image::class, name = "image"),
    JsonSubTypes.Type(value = Element.Button::class, name = "button"),
    JsonSubTypes.Type(value = Element.StaticSelect::class, name = "static_select"),
    JsonSubTypes.Type(value = Element.StaticMultiSelect::class, name = "multi_static_select"),
    JsonSubTypes.Type(value = Element.ExternalSelect::class, name = "external_select"),
    JsonSubTypes.Type(value = Element.UsersSelect::class, name = "users_select"),
    JsonSubTypes.Type(value = Element.ConversationsSelect::class, name = "conversations_select"),
    JsonSubTypes.Type(value = Element.ChannelsSelect::class, name = "channels_select"),
    JsonSubTypes.Type(value = Element.DatePicker::class, name = "datepicker"),
    JsonSubTypes.Type(value = Element.Overflow::class, name = "overflow")
)
@JacksonDataClass
sealed class Element(
    @JsonProperty("type") open val type: Type,
    open val blockId: String?
) {

    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(internal val typeString: String) {
        IMAGE("image"),
        BUTTON("button"),
        STATIC_SELECT("static_select"),
        MULTI_STATIC_SELECT("multi_static_select"),
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
     * Represents an image that can be used in sections and context blocks only.
     *
     * @property blockId The id of the according block
     * @property imageUrl The URL of the image displayed.
     * @property altText A plain text describing the image.
     *
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#image)
     */
    @JacksonDataClass
    data class Image constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("image_url") val imageUrl: String,
        @JsonProperty("alt_text") val altText: String
    ) : Element(Type.IMAGE, blockId) {
        companion object
    }

    /**
     * Represents an interactive element that acts as a button.
     *
     * @property text A [Text] objects that defines the button's text.
     * @property actionId Generated if not specified. Used to identify the Button's source of action when using interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property url A URL that will be loaded in the users browser when the button is clicked.
     * @property value A value that can be sent along with the interaction payload. (Max length 2000 chars).
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog when the button is clicked.
     *
     * @see [Text]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#button)
     */
    @JacksonDataClass
    data class Button constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("text") val text: Text,
        @JsonProperty("action_id") val actionId: String,
        @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant? = null,
        @JsonProperty("url") val url: String? = null,
        @JsonProperty("value") val value: String? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.BUTTON, blockId) {
        companion object
    }

    /**
     * A select menu with a static List of [Option]s.
     *
     * @property blockId The id of the according block
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property options A List of [Option]s. (Max 100 [Option]s).
     * @property optionGroups A List of [OptionGroup] objects. (Max 100 [OptionGroup]s).
     * @property initialOption A single [Option] that is displayed as the default option when the menu initially loads. Must match one of the [Option]s in options or optionGroups.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [Option]
     * @see [OptionGroup]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#select)
     */
    @JacksonDataClass
    data class StaticSelect constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("placeholder") val placeholderText: Text,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("options") val options: List<Option>?,
        @JsonProperty("option_groups") val optionGroups: List<OptionGroup>? = null,
        @JsonProperty("initial_option") val initialOption: Option? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.STATIC_SELECT, blockId) {
        companion object
    }


    /**
     * A multi select menu with a static List of [Option]s.
     *
     * @property blockId The id of the according block
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property options A List of [Option]s. (Max 100 [Option]s).
     * @property optionGroups A List of [OptionGroup] objects. (Max 100 [OptionGroup]s).
     * @property initialOptions A List of [Option] that is displayed as the default option when the menu initially loads. Must match [Option]s in options or optionGroups.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [Option]
     * @see [OptionGroup]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/messaging/interactivity#multi_select_menus)
     */
    @JacksonDataClass
    data class StaticMultiSelect constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("placeholder") val placeholderText: Text,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("options") val options: List<Option>?,
        @JsonProperty("option_groups") val optionGroups: List<OptionGroup>? = null,
        @JsonProperty("initial_options") val initialOptions: List<Option>? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.MULTI_STATIC_SELECT, blockId) {
        companion object
    }

    /**
     * A select menu with an external data source, enabling the usage of a dynamic list of [Option]s.
     *
     * @property blockId The id of the according block
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property options A List of [Option]s. (Max 100 [Option]s).
     * @property optionGroups A List of [OptionGroup] objects. (Max 100 [OptionGroup]s).
     * @property initialOption A single [Option] that is displayed as a pre-selected option when the menu initially loads. Must match one of the [Option]s in options or optionGroups.
     * @property minQueryLength Specifies the fewest number of typed characters before dispatching to the typeahead field to limit requests. If not used, each character will be dispatched.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [Option]
     * @see [OptionGroup]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#external-select)
     */
    @JacksonDataClass
    data class ExternalSelect constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("placeholder") val placeholderText: Text?,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("options") val options: List<Option>,
        @JsonProperty("option_groups") val optionGroups: List<OptionGroup>? = null,
        @JsonProperty("initial_option") val initialOption: Option? = null,
        @JsonProperty("min_query_length") val minQueryLength: Int? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.EXTERNAL_SELECT, blockId) {
        companion object
    }


    /**
     * A select menu with a user list.
     * This select menu will populate its options based on the Slack users visibile to the current user
     * in the active workspace.
     *
     * @property blockId The id of the according block
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property initialUserId Displays the user with that user-id as a pre-selected option when the menu initially loads.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#users-select)
     */
    @JacksonDataClass
    data class UsersSelect constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("placeholder") val placeholderText: Text?,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("selected_user") val selectedUser: String? = null,
        @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant? = null,
        @JsonProperty("initial_user")
        val initialUserId: String? = null,
        @JsonProperty("confirm")
        val confirmation: Confirmation? = null
    ) : Element(Type.USERS_SELECT, blockId) {
        companion object
    }

    /**
     * A select menu with a conversation list.
     * This select menu will populate with a list of public and private channels, direct-messages and multi-party instant messages
     * that are visible to the current user in the active workspace.
     *
     * @property blockId The id of the according block
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property initialConversationId Displays the conversation with that conversation-id as a pre-selected option when the menu initially loads.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#conversation-select)
     */
    @JacksonDataClass
    data class ConversationsSelect constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("placeholder") val placeholderText: Text?,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("selected_conversation") val selectedUser: String? = null,
        @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant? = null,
        @JsonProperty("initial_conversation") val initialConversationId: String? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.CONVERSATIONS_SELECT, blockId) {
        companion object
    }

    /**
     * A select menu with a channel list.
     * This select menu will populate with a list of public that are visible to the current user in the active workspace.
     *
     * @property blockId The id of the according block
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property initialChannelsId Displays the channel with that channel-id as a pre-selected option when the menu initially loads.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#channel-select)
     */
    @JacksonDataClass
    data class ChannelsSelect constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("placeholder") val placeholderText: Text?,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("selected_channel") val selectedUser: String? = null,
        @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant? = null,
        @JsonProperty("initial_channels") val initialChannelsId: String? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.CHANNELS_SELECT, blockId) {
        companion object
    }

    /**
     * A overflow button that presents a List of [Option]s when clicking.
     * This button appears in the form of '(...)' and offers no type-field and no customizable text.
     *
     * It is generally used for compact layouts or to supply a list of less visually important actions after a e.g. a row of buttons etc.
     *
     * @property blockId The id of the according block
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property options A List of [Option]s that is displayed in the menu. (Max 5 and Min 2 [Option]s).
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Option]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#overflow)
     */
    @JacksonDataClass
    data class Overflow constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("options") val options: List<Option>,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.OVERFLOW, blockId) {
        companion object
    }

    /**
     * An element that is used to select a date from a calendar style UI.
     * Date picker elements can be used inside of section and action blocks.
     *
     * @property blockId The id of the according block
     * @property actionId Generated if not specified. An identifier for the action triggered when a menu item is selected which is usable with interactions. Should be unique, even on iterations. (Max length 255 chars).
     * @property placeholderText A plain_text-only [Text] object defining the placeholder text shown on the menu. (Max length 150 chars).
     * @property initialDate Displays the date with with the format of YYYY-MM-DD as a pre-selected option when the menu initially loads.
     * @property confirmation A [Confirmation] object that defines an optional confirmation dialog after the menu item is selected.
     *
     * @see [Text]
     * @see [LocalDate]
     * @see [Confirmation]
     * @see [Slack API Documentation](https://api.slack.com/reference/messaging/block-elements#datepicker)
     */
    @JacksonDataClass
    data class DatePicker constructor(
        @JsonProperty("block_id") override val blockId: String? = null,
        @JsonProperty("action_id") val actionId: String,
        @JsonProperty("placeholder") val placeholderText: Text? = null,
        @JsonProperty("initial_date") val initialDate: LocalDate? = null,
        @JsonProperty("confirm") val confirmation: Confirmation? = null
    ) : Element(Type.DATE_PICKER, blockId) {
        companion object
    }
}
