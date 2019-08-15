package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsCreateMethod : ApiCallMethod<ChannelsCreateMethod, SuccessfulChannelsCreateResponse, ErrorChannelsCreateResponse, ChannelsCreateRequest>() {

}
