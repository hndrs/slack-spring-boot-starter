package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsCreateMethod : ApiCallMethod<ChannelsCreateMethod, SuccessfulChannelCreateResponse, ErrorChannelCreateResponse, ChannelCreateRequest>() {

}
