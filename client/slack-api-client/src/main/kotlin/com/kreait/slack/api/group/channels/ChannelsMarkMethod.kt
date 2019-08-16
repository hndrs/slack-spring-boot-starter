package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsMarkResponse
import com.kreait.slack.api.group.ApiCallMethod


abstract class ChannelsMarkMethod : ApiCallMethod<ChannelsMarkMethod, SuccessfulChannelsMarkResponse, ErrorChannelsMarkResponse, ChannelsMarkRequest>() {

}
