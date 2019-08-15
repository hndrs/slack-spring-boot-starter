package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelSetPurposeResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsSetPurposeMethod : ApiCallMethod<ChannelsSetPurposeMethod, SuccessfulChannelSetPurposeResponse, ErrorChannelSetPurposeResponse, ChannelSetPurposeRequest>() {

}
