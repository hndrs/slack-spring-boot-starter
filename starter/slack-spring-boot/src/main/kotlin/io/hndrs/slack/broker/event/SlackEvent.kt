package io.hndrs.slack.broker.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.broker.util.JacksonDataClass

/**
 * An event that triggered
 * @property type the type of the event
 * @property token the token that can be used for further actions
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SlackChallenge::class, name = "url_verification"),
    JsonSubTypes.Type(value = SlackEvent::class, name = "event_callback")
)
@JacksonDataClass
open class EventRequest constructor(
    @JsonProperty("type") open val type: String,
    @JsonProperty("token") open val token: String,
)

/**
 * The challenge parameter which is only used to verify the events endpoint
 */
@JacksonDataClass
data class SlackChallenge constructor(
    override val type: String,
    override val token: String,
    @JsonProperty("challenge") val challenge: String,
) : EventRequest(type, token) {
    companion object
}

/**
 *
 * An event that triggered
 *
 * @property type the type of the event
 * @property token the token that can be used for further actions
 * @property teamId the id of the team
 * @property apiAppId the id of the app
 * @property authedUsers the
 * @property eventId the id of that event
 * @property eventTime the time of the event
 * @property event the event information
 */
@JacksonDataClass
data class SlackEvent constructor(
    override val type: String,
    override val token: String,
    @JsonProperty("team_id") val teamId: String,
    @JsonProperty("api_app_id") val apiAppId: String,
    @JsonProperty("authed_users") val authedUsers: Set<String>?,
    @JsonProperty("event_id") val eventId: String,
    @JsonProperty("event_time") val eventTime: Int,
    @JsonProperty("event") val event: Map<String, Any>,
) : EventRequest(type, token) {

    fun isOfType(type: String): Boolean = this.event["type"] == type

    companion object
}
