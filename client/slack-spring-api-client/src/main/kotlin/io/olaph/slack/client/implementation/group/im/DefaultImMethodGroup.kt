package io.olaph.slack.client.implementation.group.im

import io.olaph.slack.client.group.im.ImCloseMethod
import io.olaph.slack.client.group.im.ImHistoryMethod
import io.olaph.slack.client.group.im.ImListMethod
import io.olaph.slack.client.group.im.ImMarkMethod
import io.olaph.slack.client.group.im.ImMethodGroup
import io.olaph.slack.client.group.im.ImRepliesMethod
import org.slf4j.LoggerFactory

class DefaultImMethodGroup : ImMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultImMethodGroup::class.java)
    }

    override fun history(authToken: String): ImHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String): ImListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mark(authToken: String): ImMarkMethod {
        return DefaultImMarkMethod(authToken)
    }

    override fun replies(authToken: String): ImRepliesMethod {
        return DefaultImRepliesMethod(authToken)
    }

    override fun close(authToken: String): ImCloseMethod {
        return DefaultImCloseMethod(authToken)
    }

    override fun open(authToken: String): DefaultImOpenMethod {
        return DefaultImOpenMethod(authToken)
    }
}
