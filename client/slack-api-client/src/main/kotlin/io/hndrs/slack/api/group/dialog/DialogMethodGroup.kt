package io.hndrs.slack.api.group.dialog

/**
 * Convenience class to handle the dialog operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface DialogMethodGroup {

    /**
     * Abstract representation of an slack api operation
     * https://api.slack.com/methods/dialog.open
     */
    fun open(authToken: String): io.hndrs.slack.api.group.dialog.DialogOpenMethod
}
