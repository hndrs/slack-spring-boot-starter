package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Archives a channel.
 * https://api.slack.com/methods/channels.archive
 */
abstract class ChannelsArchiveMethod : ApiCallMethod<ChannelsArchiveMethod, SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse, ChannelsArchiveRequest>() {

}
