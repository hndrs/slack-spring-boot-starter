package com.kreait.slack.api.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsAddRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/pins.add
 */
abstract class PinsAddMethod :
    ApiCallMethod<PinsAddMethod, SuccessfulPinsAddResponse, ErrorPinsAddResponse, PinsAddRequest>()
