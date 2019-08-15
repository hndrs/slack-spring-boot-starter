package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsHistoryMethod : ApiCallMethod<ChannelsHistoryMethod, SuccessfulChannelHistoryResponse, ErrorChannelHistoryResponse, ChannelsHistoryRequest>() {
}
