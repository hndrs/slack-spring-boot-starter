package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsArchiveMethod : ApiCallMethod<ChannelsArchiveMethod, SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse, ChannelsArchiveRequest>() {

}
