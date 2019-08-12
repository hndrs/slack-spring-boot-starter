package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.ImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ImHistoryMethod : ApiCallMethod<ImHistoryMethod, SuccessfulImHistoryResponse, ErrorImHistoryResponse, ImHistoryRequest>()
