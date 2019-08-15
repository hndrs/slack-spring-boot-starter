package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsLeaveMethod : ApiCallMethod<ChannelsLeaveMethod, SuccessfulChannelsLeaveResponse, ErrorChannelsLeaveResponse, ChannelsLeaveRequest>() {

}
