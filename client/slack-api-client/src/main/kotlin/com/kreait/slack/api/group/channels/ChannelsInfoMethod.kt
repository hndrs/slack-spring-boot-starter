package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsInfoMethod : ApiCallMethod<ChannelsInfoMethod, SuccessfulChannelInfoResponse, ErrorChannelInfoResponse, ChannelsInfoRequest>() {
}
