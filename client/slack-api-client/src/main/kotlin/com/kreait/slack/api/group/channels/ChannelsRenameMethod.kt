package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRenameRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRenameResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Renames a channel
 * https://api.slack.com/methods/channels.rename
 */
abstract class ChannelsRenameMethod : ApiCallMethod<ChannelsRenameMethod, SuccessfulChannelRenameResponse, ErrorChannelRenameResponse, ChannelRenameRequest>() {

}
