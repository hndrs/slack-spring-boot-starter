package io.olaph.slack.client.test.group.dialog

import io.olaph.slack.client.group.dialog.DialogMethodGroup

class MockDialogMethodGroup : DialogMethodGroup {
    private val mockDialogMethodGroup = MockDialogOpenMethod()

    override fun open(authToken: String): MockDialogOpenMethod {
        return mockDialogMethodGroup
    }
}
