package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Creates a channel
 * https://api.slack.com/methods/channels.create
 */
abstract class ChannelsCreateMethod : ApiCallMethod<ChannelsCreateMethod, SuccessfulChannelsCreateResponse, ErrorChannelsCreateResponse, ChannelsCreateRequest>() {

}
