package io.olaph.slack.client.group.conversations

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackOpenRequest
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulOpenResponse

//TODO Implement
abstract class ConversationsOpenMethod : ApiCallMethod<ConversationsOpenMethod, SuccessfulOpenResponse, ErrorOpenResponse, SlackOpenRequest>() {

}
