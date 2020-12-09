package com.kreait.slack.api.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsListRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/pins.list
 */
abstract class PinsListMethod :
    ApiCallMethod<PinsListMethod, SuccessfulPinsListResponse, ErrorPinsListResponse, PinsListRequest>()
