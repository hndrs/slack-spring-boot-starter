package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.im.ImCloseMethod
import io.olaph.slack.client.group.im.ImHistoryMethod
import io.olaph.slack.client.group.im.ImListMethod
import io.olaph.slack.client.group.im.ImMethodGroup

class MockImMethodGroup : ImMethodGroup {

    private val mockImRepliesMethod = MockImRepliesMethod()
    private val mockImOpenMethod = MockImOpenMethod()
    private val mockImMarkMethod = MockImMarkMethod()


    override fun close(authToken: String): ImCloseMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun history(authToken: String): ImHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String): ImListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
