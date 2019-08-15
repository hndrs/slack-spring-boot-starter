package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsJoinRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsJoinResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsJoinMethod : ApiCallMethod<ChannelsJoinMethod, SuccessfulChannelsJoinResponse, ErrorChannelsJoinResponse, ChannelsJoinRequest>() {
}
