package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsJoinRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsJoinResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Joins a channel, creating it if needed.
 * https://api.slack.com/methods/channels.join
 */
abstract class ChannelsJoinMethod : ApiCallMethod<ChannelsJoinMethod, SuccessfulChannelsJoinResponse, ErrorChannelsJoinResponse, ChannelsJoinRequest>() {
}
