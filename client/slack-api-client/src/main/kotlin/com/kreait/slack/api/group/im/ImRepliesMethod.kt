package com.kreait.slack.api.group.im

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.im.ErrorImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImRepliesRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImRepliesResponse

abstract class ImRepliesMethod : ApiCallMethod<ImRepliesMethod, SuccessfulImRepliesResponse, ErrorImRepliesResponse, SlackImRepliesRequest>()
