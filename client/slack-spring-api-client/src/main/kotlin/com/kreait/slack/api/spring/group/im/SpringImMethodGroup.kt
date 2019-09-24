package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.group.im.ImCloseMethod
import com.kreait.slack.api.group.im.ImHistoryMethod
import com.kreait.slack.api.group.im.ImListMethod
import com.kreait.slack.api.group.im.ImMarkMethod
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.group.im.ImRepliesMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api im method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringImMethodGroup : ImMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringImMethodGroup::class.java)
    }

    override fun history(authToken: String): ImHistoryMethod {
        return SpringImHistoryMethod(authToken)
    }

    override fun list(authToken: String): ImListMethod {
        return SpringImListMethod(authToken)
    }

    override fun mark(authToken: String): ImMarkMethod {
        return SpringImMarkMethod(authToken)
    }

    override fun replies(authToken: String): ImRepliesMethod {
        return SpringImRepliesMethod(authToken)
    }

    override fun close(authToken: String): ImCloseMethod {
        return SpringImCloseMethod(authToken)
    }

    override fun open(authToken: String): SpringImOpenMethod {
        return SpringImOpenMethod(authToken)
    }
}
