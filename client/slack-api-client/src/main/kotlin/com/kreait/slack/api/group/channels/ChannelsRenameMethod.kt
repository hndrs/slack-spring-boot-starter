package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRenameRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRenameResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsRenameMethod : ApiCallMethod<ChannelsRenameMethod, SuccessfulChannelRenameResponse, ErrorChannelRenameResponse, ChannelRenameRequest>() {

}
