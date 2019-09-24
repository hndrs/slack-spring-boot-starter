package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Sets the purpose for a channel.
 * https://api.slack.com/methods/channels.setPurpose
 */
abstract class ChannelsSetPurposeMethod : ApiCallMethod<ChannelsSetPurposeMethod, SuccessfulChannelsSetPurposeResponse, ErrorChannelsSetPurposeResponse, ChannelsSetPurposeRequest>() {

}
