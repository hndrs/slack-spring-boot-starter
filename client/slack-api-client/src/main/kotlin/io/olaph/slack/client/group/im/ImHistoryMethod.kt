package io.olaph.slack.client.group.im

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.im.ErrorImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.SlackImHistoryRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImHistoryResponse

abstract class ImHistoryMethod : ApiCallMethod<ImHistoryMethod, SuccessfulImHistoryResponse, ErrorImHistoryResponse, SlackImHistoryRequest>()
