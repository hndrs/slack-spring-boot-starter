package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelUnarchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Unarchives a channel.
 * https://api.slack.com/methods/channels.unarchive
 */
abstract class ChannelsUnarchiveMethod : ApiCallMethod<ChannelsUnarchiveMethod, SuccessfulChannelUnarchiveResponse, ErrorChannelUnarchiveResponse, ChannelsUnarchiveRequest>()
