package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsKickRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelKickResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsKickMethod : ApiCallMethod<ChannelsKickMethod, SuccessfulChannelKickResponse, ErrorChannelKickResponse, ChannelsKickRequest>() {
}
