package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsHistoryMethod : ApiCallMethod<ChannelsHistoryMethod, SuccessfulChannelsHistoryResponse, ErrorChannelsHistoryResponse, ChannelsHistoryRequest>() {
}
