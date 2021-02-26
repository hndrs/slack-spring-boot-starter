package io.hndrs.slack.api.contract.jackson.event

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

/**
 * An event that triggered
 * @property type the type of the event
 * @property token the token that can be used for further actions
 */
@JacksonDataClass
abstract class Event constructor(@JsonProperty("type") open val type: String) {

    /**
     * returns the type that is specified by slack
     * https://api.slack.com/events
     */
    abstract fun slackTypeString(): String
}
