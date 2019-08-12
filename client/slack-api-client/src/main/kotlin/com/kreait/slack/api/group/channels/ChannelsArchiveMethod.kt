package com.kreait.slack.api.group.channels

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse

abstract class ChannelsArchiveMethod : ApiCallMethod<ChannelsArchiveMethod, SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse, SlackChannelsArchiveRequest>() {

}
