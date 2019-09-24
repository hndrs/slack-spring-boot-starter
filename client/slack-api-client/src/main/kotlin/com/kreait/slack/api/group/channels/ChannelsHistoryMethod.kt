package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Fetches history of messages and events from a channel.
 * https://api.slack.com/methods/channels.history
 */
abstract class ChannelsHistoryMethod : ApiCallMethod<ChannelsHistoryMethod, SuccessfulChannelsHistoryResponse, ErrorChannelsHistoryResponse, ChannelsHistoryRequest>() {
}
