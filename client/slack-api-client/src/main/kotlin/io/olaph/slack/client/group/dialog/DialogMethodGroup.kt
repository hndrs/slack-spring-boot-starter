package io.olaph.slack.client.group.dialog

interface DialogMethodGroup {
    fun open(authToken:String): DialogOpenMethod
}
