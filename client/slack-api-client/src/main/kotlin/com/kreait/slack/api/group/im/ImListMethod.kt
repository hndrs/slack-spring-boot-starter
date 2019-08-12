package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImListRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ImListMethod : ApiCallMethod<ImListMethod, SuccessfulImListResponse, ErrorImListResponse, SlackImListRequest>()
