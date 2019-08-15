package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelMarkResponse
import com.kreait.slack.api.group.ApiCallMethod


abstract class ChannelsMarkMethod : ApiCallMethod<ChannelsMarkMethod, SuccessfulChannelMarkResponse, ErrorChannelMarkResponse, ChannelMarkRequest>() {

}