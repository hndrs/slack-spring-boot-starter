package io.hndrs.slack.broker.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.slack.api.model.event.Event
import com.slack.api.util.json.GsonFactory
import io.hndrs.slack.broker.store.team.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders
import kotlin.reflect.KClass
import kotlin.reflect.full.staticProperties

/**
 * EventReceiver that should be implemented to react to incoming events
 */
interface EventReceiver {

    /**
     * Method that determines if the incoming event should be handled by the implementing receiver
     *
     * @param slackEvent the incoming event
     */
    fun supportsEvent(slackEvent: SlackEvent): Boolean = true

    /**
     * will be executed if [supportsEvent] returned true
     *
     * @param slackEvent the incoming event
     * @param team the team with the access-token which can be used for further actions
     */
    fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team)

    /**
     * Method that determines if an exception in this receiver should be thrown
     * This will cause that the [EventBroker] will not execute any other event receiver after an error
     *
     * @param exception the exception that occurred
     */
    fun shouldThrowException(exception: Exception): Boolean = false

    /**
     * receivers will be sorted ascending by this order
     */
    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}

abstract class TypedEventReceiver<T : Event>(private val clazz: KClass<T>) : EventReceiver {

    final override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        val event = GsonFactory.createSnakeCase()
            .fromJson(jacksonObjectMapper().writeValueAsString(slackEvent.event), clazz.java)

        if (supports(event)) {
            onReceiveEvent(event, headers, team)
        }
    }

    abstract fun onReceiveEvent(event: T, headers: HttpHeaders, team: Team)

    final override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return slackEvent.event["type"] == clazz.staticProperties.first { it.name == "TYPE_NAME" }.get() as String
    }

    open fun supports(event: T): Boolean = true
}
