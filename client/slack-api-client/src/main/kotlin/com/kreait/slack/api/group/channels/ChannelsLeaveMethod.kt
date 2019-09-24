package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Leaves a channel.
 * https://api.slack.com/methods/channels.leave
 */
abstract class ChannelsLeaveMethod : ApiCallMethod<ChannelsLeaveMethod, SuccessfulChannelsLeaveResponse, ErrorChannelsLeaveResponse, ChannelsLeaveRequest>() {

}
