package com.kreait.slack.api.group.channels

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelCreateResponse

abstract class ChannelsCreateMethod : ApiCallMethod<ChannelsCreateMethod, SuccessfulChannelCreateResponse, ErrorChannelCreateResponse, SlackChannelCreateRequest>() {

}
