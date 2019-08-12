package com.kreait.slack.api.group.im

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse

abstract class ImHistoryMethod : ApiCallMethod<ImHistoryMethod, SuccessfulImHistoryResponse, ErrorImHistoryResponse, SlackImHistoryRequest>()
