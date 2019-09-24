package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Gets information about a channel.
 * https://api.slack.com/methods/channels.info
 */
abstract class ChannelsInfoMethod : ApiCallMethod<ChannelsInfoMethod, SuccessfulChannelInfoResponse, ErrorChannelInfoResponse, ChannelsInfoRequest>() {
}
