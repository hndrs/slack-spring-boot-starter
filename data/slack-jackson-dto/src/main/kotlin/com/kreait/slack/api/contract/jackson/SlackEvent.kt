package com.kreait.slack.api.contract.jackson

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SlackChallenge::class, name = "url_verification"),
        JsonSubTypes.Type(value = SlackEvent::class, name = "event_callback")
)
@JacksonDataClass
abstract class EventRequest constructor(@JsonProperty("type") open val type: String,
                                        @JsonProperty("token") open val token: String)

@JacksonDataClass
data class SlackChallenge constructor(
        override val type: String,
        override val token: String,
        @JsonProperty("challenge") val challenge: String)
    : EventRequest(type, token)

@JacksonDataClass
data class SlackEvent constructor(
        override val type: String,
        override val token: String,
        @JsonProperty("team_id") val teamId: String,
        @JsonProperty("api_app_id") val apiAppId: String,
        @JsonProperty("authed_users") val authedUsers: Set<String>?,
        @JsonProperty("event_id") val eventId: String,
        @JsonProperty("event_time") val eventTime: Int,
        @JsonProperty("event") val event: Map<String, Any>
) : EventRequest(type, token) {
    companion object
}
