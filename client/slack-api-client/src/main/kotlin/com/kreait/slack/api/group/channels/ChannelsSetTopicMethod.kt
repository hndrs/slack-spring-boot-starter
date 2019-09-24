package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetTopicResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sets the topic for a channel.
 * https://api.slack.com/methods/channels.setTopic
 */
abstract class ChannelsSetTopicMethod : ApiCallMethod<ChannelsSetTopicMethod, SuccessfulChannelsSetTopicResponse, ErrorChannelsSetTopicResponse, ChannelsSetTopicRequest>() {

}

