package com.kreait.slack.api.spring.group.dialog

import com.kreait.slack.api.group.dialog.DialogMethodGroup
import com.kreait.slack.api.group.dialog.DialogOpenMethod
import org.slf4j.LoggerFactory

class SpringDialogMethodGroup : DialogMethodGroup {
    override fun open(authToken: String): DialogOpenMethod {
        return SpringDialogOpenMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(SpringDialogMethodGroup::class.java)
    }
}
