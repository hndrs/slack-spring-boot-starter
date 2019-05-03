package io.olaph.slack.client.group.im

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImListResponse
import io.olaph.slack.dto.jackson.group.im.SlackImListRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImListResponse

abstract class ImListMethod : ApiCallMethod<ImListMethod, SuccessfulImListResponse, ErrorImListResponse, SlackImListRequest>()
