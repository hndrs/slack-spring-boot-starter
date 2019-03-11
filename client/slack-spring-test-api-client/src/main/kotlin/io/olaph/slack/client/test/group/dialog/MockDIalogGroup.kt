package io.olaph.slack.client.test.group.dialog

import io.olaph.slack.client.group.dialog.DialogMethodGroup
import io.olaph.slack.client.group.dialog.DialogOpenMethod

class MockDialogMethodGroup : DialogMethodGroup {

    override fun open(authToken: String): DialogOpenMethod {
        return MockDialogOpenMethod()
    }
}
