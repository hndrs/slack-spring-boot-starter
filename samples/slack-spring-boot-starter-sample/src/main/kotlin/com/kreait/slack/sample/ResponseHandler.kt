package com.kreait.slack.sample

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResponseHandler @Autowired constructor(private val slackClient: SlackClient) {

    fun successResponse(channelId: String, authToken: String) {
        slackClient.chat()
                .postEphemeral(authToken)
                .with(PostEphemeralRequest(text = "successfully responded", channel = channelId, user = ""))
                .invoke()
    }

    fun failureResponse(channelId: String, authToken: String) {
        slackClient.chat()
                .postEphemeral(authToken)
                .with(PostEphemeralRequest(text = "Oh no, something went wrong", channel = channelId, user = ""))
                .invoke()
    }

}
