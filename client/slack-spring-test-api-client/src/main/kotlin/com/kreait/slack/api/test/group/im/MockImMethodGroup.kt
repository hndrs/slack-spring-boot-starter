package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.group.im.ImMethodGroup

class MockImMethodGroup : ImMethodGroup {

    private val mockImRepliesMethod = MockImRepliesMethod()
    private val mockImOpenMethod = MockImOpenMethod()
    private val mockImMarkMethod = MockImMarkMethod()
    private val mockImListMethod = MockImListMethod()
    private val mockImHistoryMethod = MockImHistoryMethod()
    private val mockImCloseMethod = MockImCloseMethod()


    override fun close(authToken: String): MockImCloseMethod {
        return mockImCloseMethod
    }

    override fun history(authToken: String): MockImHistoryMethod {
        return mockImHistoryMethod
    }

    override fun list(authToken: String): MockImListMethod {
        return mockImListMethod
    }

    override fun mark(authToken: String): MockImMarkMethod {
        return mockImMarkMethod
    }

    override fun open(authToken: String): MockImOpenMethod {
        return mockImOpenMethod
    }

    override fun replies(authToken: String): MockImRepliesMethod {
        return mockImRepliesMethod
    }
}
