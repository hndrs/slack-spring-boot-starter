package io.hndrs.slack.broker.interactive

import com.google.gson.Gson
import com.slack.api.util.json.GsonFactory
import io.hndrs.slack.broker.interactive.views.ViewClosedPayload
import io.hndrs.slack.broker.interactive.views.ViewClosedReceiver
import io.hndrs.slack.broker.store.team.TeamStore
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class InteractiveComponentBroker(
    private val blockActionReceivers: Set<BlockActionReceiver>,
    private val viewClosedReceivers: Set<ViewClosedReceiver>,
    private val teamStore: TeamStore,
    private val gson: Gson = GsonFactory.createSnakeCase(),
) {

    /**
     * Endpoint that receives the commands
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
        path = ["/interactive-components"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun receiveCommand(@RequestParam("payload") payloadJson: String, @RequestHeader headers: HttpHeaders) {
        val payload = gson.fromJson(payloadJson, Payload::class.java)
        val team = this.teamStore.findById(payload.team.id)

        when (payload.type) {
            BlockActionPayload.TYPE -> {
                val blockActionPayload = gson.fromJson(payloadJson, BlockActionPayload::class.java)
                blockActionReceivers.filter {
                    it.supports(blockActionPayload)
                }.forEach {
                    it.onBlockAction(blockActionPayload, headers, team)
                }
            }

            ViewClosedPayload.TYPE -> {
                val viewClosedPayload = gson.fromJson(payloadJson, ViewClosedPayload::class.java)
                viewClosedReceivers.filter {
                    it.supports(viewClosedPayload)
                }.forEach {
                    it.onViewClosed(viewClosedPayload, headers, team)
                }
            }

            else -> LOG.error("Interactive Payload type `{}` not implemented", payload.type)
        }
    }

    /**
     * Helper Json Class to extract payload type and team info from json before parsing
     * to the actual object
     */
    internal data class Payload(
        val type: String,
        val team: Team,
    ) {
        data class Team(
            val id: String,
        )
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(InteractiveComponentBroker::class.java)
    }

}

