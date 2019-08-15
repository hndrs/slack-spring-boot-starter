package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsSetPurposeMethod : ApiCallMethod<ChannelsSetPurposeMethod, SuccessfulChannelsSetPurposeResponse, ErrorChannelsSetPurposeResponse, ChannelsSetPurposeRequest>() {

}
