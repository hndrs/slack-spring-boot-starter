package io.olaph.slack.client.group.im

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse

abstract class ImMarkMethod : ApiCallMethod<ImMarkMethod, SuccessfulImMarkResponse, ErrorImMarkResponse, SlackImMarkRequest>()
