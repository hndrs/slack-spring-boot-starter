package io.olaph.slack.client.spring.group.dialog

import io.olaph.slack.client.group.dialog.DialogMethodGroup
import io.olaph.slack.client.group.dialog.DialogOpenMethod
import org.slf4j.LoggerFactory

class DefaultDialogMethodGroup : DialogMethodGroup {
    override fun open(authToken: String): DialogOpenMethod {
        return DefaultDialogOpenMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultDialogMethodGroup::class.java)
    }
}
