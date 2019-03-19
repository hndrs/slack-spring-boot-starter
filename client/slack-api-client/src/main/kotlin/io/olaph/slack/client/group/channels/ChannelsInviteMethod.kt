package io.olaph.slack.client.group.channels

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelInviteRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelInviteResponse

abstract class ChannelsInviteMethod : ApiCallMethod<ChannelsInviteMethod, SuccessfulChannelInviteResponse, ErrorChannelInviteResponse, SlackChannelInviteRequest>() 
