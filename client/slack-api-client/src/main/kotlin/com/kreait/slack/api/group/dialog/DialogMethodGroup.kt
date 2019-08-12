package com.kreait.slack.api.group.dialog

interface DialogMethodGroup {
    fun open(authToken:String): DialogOpenMethod
}
