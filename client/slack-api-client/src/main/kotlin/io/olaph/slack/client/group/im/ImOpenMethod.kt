package io.olaph.slack.client.group.im

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImOpenResponse
import io.olaph.slack.dto.jackson.group.im.SlackImOpenRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImOpenResponse

abstract class ImOpenMethod : ApiCallMethod<ImOpenMethod, SuccessfulImOpenResponse, ErrorImOpenResponse, SlackImOpenRequest>()
