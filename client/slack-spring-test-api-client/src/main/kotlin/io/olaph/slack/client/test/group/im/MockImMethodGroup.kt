package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.im.ImCloseMethod
import io.olaph.slack.client.group.im.ImMethodGroup

class MockImMethodGroup : ImMethodGroup {

    private val mockImRepliesMethod = MockImRepliesMethod()
    private val mockImOpenMethod = MockImOpenMethod()
    private val mockImMarkMethod = MockImMarkMethod()
    private val mockImListMethod = MockImListMethod()
    private val mockImHistoryMethod = MockImHistoryMethod()

    override fun close(authToken: String): ImCloseMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
