package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImRepliesRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ImRepliesMethod : ApiCallMethod<ImRepliesMethod, SuccessfulImRepliesResponse, ErrorImRepliesResponse, SlackImRepliesRequest>()
