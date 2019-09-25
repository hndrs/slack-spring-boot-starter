package com.kreait.slack.api.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.ImListRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/im.listGroups
 */
abstract class ImListMethod : ApiCallMethod<ImListMethod, SuccessfulImListResponse, ErrorImListResponse, ImListRequest>()
