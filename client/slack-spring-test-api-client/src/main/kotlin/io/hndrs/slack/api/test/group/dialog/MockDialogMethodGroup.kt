package io.hndrs.slack.api.test.group.dialog

import io.hndrs.slack.api.group.dialog.DialogMethodGroup

/**
 * Testable implementation of [DialogMethodGroup]
 */
class MockDialogMethodGroup : io.hndrs.slack.api.group.dialog.DialogMethodGroup {

    private val mockDialogMethodGroup = MockDialogOpenMethod()

    override fun open(authToken: String) = mockDialogMethodGroup
}
