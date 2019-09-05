package com.kreait.slack.api.contract.jackson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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
import com.kreait.slack.api.contract.jackson.common.Action
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.messaging.Element
import com.kreait.slack.api.contract.jackson.group.chat.Message
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = InteractiveMessage::class, name = "interactive_message"),
        JsonSubTypes.Type(value = BlockActions::class, name = "block_actions"),
        JsonSubTypes.Type(value = InteractiveMessage::class, name = "dialog_submission"))
@JacksonDataClass
abstract class InteractiveComponentResponse(
        @JsonProperty("type") val type: Type?,
        open val team: Team) {
    companion object {}
    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(internal val typeString: String) {
        INTERACTIVE_MESSAGE("interactive_message"),
        BLOCK_ACTIONS("block_actions"),
        DIALOG_SUBMISSION("interactive_message");

        class Serializer : JsonSerializer<Type>() {
            override fun serialize(value: Type?, gen: JsonGenerator?, provider: SerializerProvider?) {
                gen?.writeString(value.toString().toLowerCase())
            }
        }

        class Deserializer : JsonDeserializer<Type>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Type {
                return valueOf(p.text.toUpperCase())
            }
        }
    }

    @JacksonDataClass
    data class Team(@JsonProperty("id") val id: String,
                    @JsonProperty("domain") val domain: String,
                    @JsonProperty("enterprise_id") val enterpriseId: String?,
                    @JsonProperty("enterprise_name") val enterpriseName: String?) {
        companion object
    }
}

@JacksonDataClass
data class InteractiveMessage(
        @JsonProperty("team") override val team: Team,
        @JsonProperty("token") val token: String?,
        @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant?,
        @InstantToString @JsonProperty("message_ts") val timestamp: Instant?,
        @JsonProperty("user") val user: User,
        @JsonProperty("state") val state: String? = "",
        @JsonProperty("channel") val channel: Channel,
        @JsonProperty("submission") val submission: Map<String, Any>?,
        @JsonProperty("callback_id") val callbackId: String?,
        @JsonProperty("response_url") val responseUrl: String?,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("original_message") val originalMessage: Message? = null,
        @JsonProperty("blocks") val blocks: List<Block>? = listOf(),
        @JsonProperty("container") val container: Container? = null,
        @JsonProperty("trigger_id") val triggerId: String?,
        @JsonProperty("message") val message: Message?,
        @JsonProperty("is_app_unfurl") val isAppUnfurl: String? = null,
        @JsonProperty("attachment_id") val attachmentId: String? = null) : InteractiveComponentResponse(Type.INTERACTIVE_MESSAGE, team) {
    companion object
}

@JacksonDataClass
@JsonIgnoreProperties(ignoreUnknown = true)
data class BlockActions(@JsonProperty("team") override val team: Team,
                        @JsonProperty("user") val user: User,
                        @JsonProperty("token") val token: String?,
                        @JsonProperty("api_app_id") val apiAppId: String?,
                        @JsonProperty("container") val container: Container? = null,
                        @JsonProperty("trigger_id") val triggerId: String?,
                        @JsonProperty("channel") val channel: Channel,
                        @JsonProperty("response_url") val responseUrl: String?,
                        @JsonProperty("actions") val actions: List<Element>? = listOf()) : InteractiveComponentResponse(Type.BLOCK_ACTIONS, team) {
    companion object
}

@JacksonDataClass
data class Channel(@JsonProperty("id") val id: String,
                   @JsonProperty("name") val name: String) {
    companion object
}

@JacksonDataClass
data class User(@JsonProperty("id") val id: String,
                @JsonProperty("name") val name: String,
                @JsonProperty("username") val username: String? = null,
        //TODO can this really be optional?
                @JsonProperty("team_id") val teamId: String?) {
    companion object
}


data class Container(
        @JsonProperty("channel_id") val channelId: String?,
        @JsonProperty("is_ephemeral") val isEphemeral: Boolean?,
        @JsonProperty("message_ts") val messageTs: String?,
        @JsonProperty("type") val type: String?
)