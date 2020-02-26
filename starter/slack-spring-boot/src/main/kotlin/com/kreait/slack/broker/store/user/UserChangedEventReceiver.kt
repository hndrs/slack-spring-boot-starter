package com.kreait.slack.broker.store.user

import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.receiver.TypedEventReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * Eventreceiver that updates a user in the registered store when it changes
 */
class UserChangedEventReceiver @Autowired constructor(private val userStore: UserStore) : TypedEventReceiver<Event.UserChange>(Event.UserChange::class.java) {

    override fun onReceiveEvent(slackEvent: SlackEvent, event: Event.UserChange, headers: HttpHeaders, team: Team) {
        userStore.update(userOfMember(event.member))
    }
}
