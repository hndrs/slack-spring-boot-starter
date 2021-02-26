package io.hndrs.slack.api.group.dialog

import io.hndrs.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.OpenDialogRequest
import io.hndrs.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/dialog.open
 */
abstract class DialogOpenMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.dialog.DialogOpenMethod, SuccessfulOpenDialogResponse, ErrorOpenDialogResponse, OpenDialogRequest>()
