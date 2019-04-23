package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.im.*

class MockImMethodGroup : ImMethodGroup {

    private val mockImRepliesMethod = MockImRepliesMethod()
    private val mockImOpenMethod = MockImOpenMethod()

    override fun close(authToken: String): ImCloseMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun history(authToken: String): ImHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String): ImListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mark(authToken: String): ImMarkMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun open(authToken: String): MockImOpenMethod {
        return mockImOpenMethod
    }

    override fun replies(authToken: String): MockImRepliesMethod {
        return mockImRepliesMethod
    }
}
