package io.olaph.slack.client.group.im

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SlackImRepliesRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImRepliesResponse

abstract class ImRepliesMethod : ApiCallMethod<ImRepliesMethod, SuccessfulImRepliesResponse, ErrorImRepliesResponse, SlackImRepliesRequest>()
