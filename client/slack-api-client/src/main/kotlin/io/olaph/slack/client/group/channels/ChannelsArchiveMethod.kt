package io.olaph.slack.client.group.channels

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsArchiveRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse

abstract class ChannelsArchiveMethod : ApiCallMethod<ChannelsArchiveMethod, SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse, SlackChannelsArchiveRequest>() {

}
