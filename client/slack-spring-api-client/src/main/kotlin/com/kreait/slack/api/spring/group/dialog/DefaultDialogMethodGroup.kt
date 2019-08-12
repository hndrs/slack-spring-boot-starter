package com.kreait.slack.api.spring.group.dialog

import com.kreait.slack.api.group.dialog.DialogMethodGroup
import com.kreait.slack.api.group.dialog.DialogOpenMethod
import org.slf4j.LoggerFactory

class DefaultDialogMethodGroup : DialogMethodGroup {
    override fun open(authToken: String): DialogOpenMethod {
        return DefaultDialogOpenMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultDialogMethodGroup::class.java)
    }
}
