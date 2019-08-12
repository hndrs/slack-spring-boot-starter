package com.kreait.slack.api.group.channels

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.channels.ErrorGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulGetChannelInfoResponse

abstract class ChannelsInfoMethod : ApiCallMethod<ChannelsInfoMethod, SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse, SlackChannelsInfoRequest>() {
}
