package com.kreait.slack.broker.receiver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that should be implemented to react to incoming events
 */
abstract class TypedEventReceiver<T>(private val type: Class<T>) : EventReceiver {

    private val jacksonObjectMapper: ObjectMapper = jacksonObjectMapper()

    final override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return try {
            val convertedValue = jacksonObjectMapper.convertValue(slackEvent.event, type)
            this.supportsEvent(slackEvent, convertedValue)
        } catch (e: Exception) {
            false
        }
    }

    final override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        val convertedValue = jacksonObjectMapper.convertValue(slackEvent.event, type)
        this.onReceiveEvent(slackEvent, convertedValue, headers, team)
    }

    /**
     * Method that determines if the incoming event should be handled by the implementing receiver
     *
     * @param slackEvent the incoming event
     * @param data typed converted value
     */
    fun supportsEvent(slackEvent: SlackEvent, data: T): Boolean = true

    /**
     * will be executed if [supportsEvent] returned true
     *
     * @param slackEvent the incoming event
     * @param team the team with the access-token which can be used for further actions
     */
    abstract fun onReceiveEvent(slackEvent: SlackEvent, data: T, headers: HttpHeaders, team: Team)

}
