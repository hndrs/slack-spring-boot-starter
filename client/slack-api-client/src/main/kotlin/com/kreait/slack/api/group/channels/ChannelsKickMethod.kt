package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsKickRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelKickResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Removes a user from a channel.
 * https://api.slack.com/methods/channels.kick
 */
abstract class ChannelsKickMethod : ApiCallMethod<ChannelsKickMethod, SuccessfulChannelKickResponse, ErrorChannelKickResponse, ChannelsKickRequest>() {
}
