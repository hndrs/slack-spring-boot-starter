package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod


abstract class ChannelsRepliesMethod : ApiCallMethod<ChannelsRepliesMethod, SuccessfulChannelRepliesResponse, ErrorChannelRepliesResponse, ChannelRepliesRequest>() {

}
