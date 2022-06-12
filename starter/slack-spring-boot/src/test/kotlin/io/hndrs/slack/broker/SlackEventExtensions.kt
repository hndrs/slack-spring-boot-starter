package io.hndrs.slack.broker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.slack.api.model.event.Event
import com.slack.api.model.event.UserChangeEvent
import com.slack.api.util.json.GsonFactory
import io.hndrs.slack.api.contract.jackson.event.SlackChallenge
import io.hndrs.slack.api.contract.jackson.event.SlackEvent

fun SlackEvent.Companion.sample(): SlackEvent {
    return SlackEvent(
        "",
        "",
        "",
        "",
        setOf(),
        "",
        0,
        mapOf()
    )
}

fun <T : Event> SlackEvent.Companion.sample(any: T): SlackEvent {
    val json = GsonFactory.createSnakeCase().toJson(any)
    val mapValue: Map<String, Any> = jacksonObjectMapper().readValue(json, Map::class.java) as Map<String, Any>
    return SlackEvent(
        "",
        "",
        "",
        "",
        setOf(),
        "",
        0,
        mapValue
    )
}

fun SlackChallenge.Companion.sample(): SlackChallenge {
    return SlackChallenge("", "", "")
}

