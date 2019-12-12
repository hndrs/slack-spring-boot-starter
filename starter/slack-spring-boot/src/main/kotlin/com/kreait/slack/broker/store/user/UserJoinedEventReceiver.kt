package com.kreait.slack.broker.store.user

import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that adds a user to the registered store when a new user joins the team
 */
class UserJoinedEventReceiver @Autowired constructor(private val userStore: UserStore) : EventReceiver<Event.TeamJoin> {

    override fun supportsEvent(slackEvent: SlackEvent<Event.TeamJoin>): Boolean {
        return slackEvent.event.type == Event.TeamJoin.TYPE
    }

    override fun onReceiveEvent(slackEvent: SlackEvent<Event.TeamJoin>, headers: HttpHeaders, team: Team) {
        userStore.put(userOfMember(slackEvent.event.member))
    }
}

