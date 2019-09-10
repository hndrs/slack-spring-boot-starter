package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.BlockActions
import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.common.messaging.Element
import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.ResponseType
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.Team
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class RockPaperScissorsSubmissionReceiver @Autowired constructor(private val rpsGameHandler: RPSGameHandler)
    : InteractiveComponentReceiver<BlockActions> {

    override fun supportsInteractiveMessage(interactiveComponentResponse: BlockActions): Boolean {
        return interactiveComponentResponse.actions?.let {
            return (it.first().blockId == RockPaperScissorsCommandReceiver.RPS_BLOCK_ID) && interactiveComponentResponse.type == InteractiveComponentResponse.Type.BLOCK_ACTIONS
        } ?: false
    }

    override fun onReceiveInteractiveMessage(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
        rpsGameHandler.selectionHandler(interactiveComponentResponse)
    }
}