package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.group.im.ImMethodGroup

/**
 * Testable implementation of [ImMethodGroup]
 */
class MockImMethodGroup : ImMethodGroup {

    private val mockImRepliesMethod = MockImRepliesMethod()
    private val mockImOpenMethod = MockImOpenMethod()
    private val mockImMarkMethod = MockImMarkMethod()
    private val mockImListMethod = MockImListMethod()
    private val mockImHistoryMethod = MockImHistoryMethod()
    private val mockImCloseMethod = MockImCloseMethod()

    override fun close(authToken: String) = mockImCloseMethod
    override fun history(authToken: String) = mockImHistoryMethod
    override fun list(authToken: String) = mockImListMethod
    override fun mark(authToken: String) = mockImMarkMethod
    override fun open(authToken: String) = mockImOpenMethod
    override fun replies(authToken: String) = mockImRepliesMethod
}
