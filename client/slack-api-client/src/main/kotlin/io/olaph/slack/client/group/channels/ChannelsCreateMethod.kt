package io.olaph.slack.client.group.channels

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelCreateRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelCreateResponse

abstract class ChannelsCreateMethod : ApiCallMethod<ChannelsCreateMethod, SuccessfulChannelCreateResponse, ErrorChannelCreateResponse, SlackChannelCreateRequest>() {

}
