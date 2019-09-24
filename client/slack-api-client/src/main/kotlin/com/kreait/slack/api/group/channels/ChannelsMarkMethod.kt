package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsMarkResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sets the read cursor in a channel.
 * https://api.slack.com/methods/channels.mark
 */
abstract class ChannelsMarkMethod : ApiCallMethod<ChannelsMarkMethod, SuccessfulChannelsMarkResponse, ErrorChannelsMarkResponse, ChannelsMarkRequest>() {

}
