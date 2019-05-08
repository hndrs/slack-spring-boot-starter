package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.group.im.*
import org.slf4j.LoggerFactory

class DefaultImMethodGroup : ImMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultImMethodGroup::class.java)
    }

    override fun history(authToken: String): ImHistoryMethod {
        return DefaultImHistoryMethod(authToken)
    }

    override fun list(authToken: String): ImListMethod {
        return DefaultImListMethod(authToken)
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
