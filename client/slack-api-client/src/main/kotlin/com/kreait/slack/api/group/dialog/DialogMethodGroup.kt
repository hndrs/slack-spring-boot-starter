package com.kreait.slack.api.group.dialog

import com.kreait.slack.api.group.dialog.DialogOpenMethod

interface DialogMethodGroup {
    fun open(authToken:String): DialogOpenMethod
}
