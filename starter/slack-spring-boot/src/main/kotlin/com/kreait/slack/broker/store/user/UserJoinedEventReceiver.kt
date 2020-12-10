package com.kreait.slack.broker.store.user

import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.api.contract.jackson.event.type.team.TeamJoin
import com.kreait.slack.broker.receiver.TypedEventReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that adds a user to the registered store when a new user joins the team
 */
class UserJoinedEventReceiver @Autowired constructor(private val userStore: UserStore) :
    TypedEventReceiver<TeamJoin>(TeamJoin::class.java) {

    override fun onReceiveEvent(slackEvent: SlackEvent, event: TeamJoin, headers: HttpHeaders, team: Team) {
        userStore.put(userOfMember(event.member))
    }
}

