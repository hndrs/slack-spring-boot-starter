package com.kreait.slack.broker.store.user

import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.receiver.adapter.TypedEventReceiverAdapter
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that adds a user to the registered store when a new user joins the team
 */
class UserJoinedEventReceiver @Autowired constructor(private val userStore: UserStore) : TypedEventReceiverAdapter<Event.TeamJoin>(Event.TeamJoin.TYPE) {

    override fun onReceive(slackEvent: SlackEvent<Event.TeamJoin>, headers: HttpHeaders, team: Team) {
        userStore.put(userOfMember(slackEvent.event.member))
    }
}

