package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.contract.jackson.BlockActions
import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.Team
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