package io.olaph.slack.client.group.channels

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsInfoRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulGetChannelInfoResponse

abstract class ChannelsInfoMethod : ApiCallMethod<ChannelsInfoMethod, SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse, SlackChannelsInfoRequest>() {
}
