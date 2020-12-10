package com.kreait.slack.api.group.dialog

import com.kreait.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.OpenDialogRequest
import com.kreait.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/dialog.open
 */
abstract class DialogOpenMethod :
    ApiCallMethod<DialogOpenMethod, SuccessfulOpenDialogResponse, ErrorOpenDialogResponse, OpenDialogRequest>()
