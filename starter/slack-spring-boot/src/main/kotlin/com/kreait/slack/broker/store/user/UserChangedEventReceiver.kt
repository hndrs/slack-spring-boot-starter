package com.kreait.slack.broker.store.user

import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * Eventreceiver that updates a user in the registered store when it changes
 */
class UserChangedEventReceiver @Autowired constructor(private val userStore: UserStore) : EventReceiver<Event.UserChange> {

    override fun supportsEvent(slackEvent: SlackEvent<Event.UserChange>): Boolean {
        return slackEvent.event.type == Event.UserChange.TYPE
    }

    override fun onReceiveEvent(slackEvent: SlackEvent<Event.UserChange>, headers: HttpHeaders, team: Team) {
        userStore.update(userOfMember(slackEvent.event.member))
    }
}
