package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod


abstract class ChannelsRepliesMethod : ApiCallMethod<ChannelsRepliesMethod, SuccessfulChannelsRepliesResponse, ErrorChannelsRepliesResponse, ChannelsRepliesRequest>() {

}
