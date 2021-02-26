package io.hndrs.slack.broker.store.user

import io.hndrs.slack.api.contract.jackson.event.SlackEvent
import io.hndrs.slack.api.contract.jackson.event.type.user.UserChange
import io.hndrs.slack.broker.receiver.TypedEventReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

/**
 * Eventreceiver that updates a user in the registered store when it changes
 */
class UserChangedEventReceiver @Autowired constructor(private val userStore: UserStore) :
    TypedEventReceiver<UserChange>(UserChange::class.java) {

    override fun onReceiveEvent(slackEvent: SlackEvent, event: UserChange, headers: HttpHeaders, team: Team) {
        userStore.update(userOfMember(event.member))
    }
}
