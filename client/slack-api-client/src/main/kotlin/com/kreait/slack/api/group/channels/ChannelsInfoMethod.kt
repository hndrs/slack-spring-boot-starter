package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulGetChannelInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsInfoMethod : ApiCallMethod<ChannelsInfoMethod, SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse, SlackChannelsInfoRequest>() {
}
