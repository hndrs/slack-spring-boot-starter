package io.hndrs.slack.api.spring.group.dialog

import io.hndrs.slack.api.group.dialog.DialogMethodGroup
import io.hndrs.slack.api.group.dialog.DialogOpenMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api dialog method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringDialogMethodGroup : io.hndrs.slack.api.group.dialog.DialogMethodGroup {
    override fun open(authToken: String): io.hndrs.slack.api.group.dialog.DialogOpenMethod {
        return SpringDialogOpenMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(SpringDialogMethodGroup::class.java)
    }
}
