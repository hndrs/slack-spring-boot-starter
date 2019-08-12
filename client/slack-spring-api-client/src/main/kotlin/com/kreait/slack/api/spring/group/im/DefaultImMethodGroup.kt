package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.group.im.ImCloseMethod
import com.kreait.slack.api.group.im.ImHistoryMethod
import com.kreait.slack.api.group.im.ImListMethod
import com.kreait.slack.api.group.im.ImMarkMethod
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.group.im.ImRepliesMethod
import com.kreait.slack.client.group.im.*
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
