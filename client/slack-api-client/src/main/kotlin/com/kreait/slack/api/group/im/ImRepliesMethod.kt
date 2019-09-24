package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.ImRepliesRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/im.replies
 */
abstract class ImRepliesMethod : ApiCallMethod<ImRepliesMethod, SuccessfulImRepliesResponse, ErrorImRepliesResponse, ImRepliesRequest>()
