package io.olaph.slack.client.test.group.im

import io.olaph.slack.client.group.im.ImCloseMethod
import io.olaph.slack.client.group.im.ImHistoryMethod
import io.olaph.slack.client.group.im.ImListMethod
import io.olaph.slack.client.group.im.ImMarkMethod
import io.olaph.slack.client.group.im.ImMethodGroup
import io.olaph.slack.client.group.im.ImOpenMethod
import io.olaph.slack.client.group.im.ImRepliesMethod

class MockImMethodGroup : ImMethodGroup {

    private val mockImRepliesMethod = MockImRepliesMethod()

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

    override fun open(authToken: String): ImOpenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): ImRepliesMethod {
        return mockImRepliesMethod
    }
}
