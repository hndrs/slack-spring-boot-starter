package io.olaph.slack.client.group.dialog

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogRequest
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse

abstract class DialogOpenMethod : ApiCallMethod<DialogOpenMethod, SuccessfulOpenDialogResponse, ErrorOpenDialogResponse, SlackOpenDialogRequest>()
