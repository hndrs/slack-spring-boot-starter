package com.kreait.slack.api.group.pins

import com.kreait.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import com.kreait.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import com.kreait.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/pins.remove
 */
abstract class PinsRemoveMethod : ApiCallMethod<PinsRemoveMethod, SuccessfulPinsRemoveResponse, ErrorPinsRemoveResponse, PinsRemoveRequest>()
