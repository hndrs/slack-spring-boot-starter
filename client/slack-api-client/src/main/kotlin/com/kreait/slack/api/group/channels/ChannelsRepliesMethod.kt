package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Retrieve a thread of messages posted to a channel
 * https://api.slack.com/methods/channels.replies
 */
abstract class ChannelsRepliesMethod : ApiCallMethod<ChannelsRepliesMethod, SuccessfulChannelsRepliesResponse, ErrorChannelsRepliesResponse, ChannelsRepliesRequest>() {

}
