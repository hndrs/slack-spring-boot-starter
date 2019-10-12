package com.kreait.slack.api.spring.group.pins

import com.kreait.slack.api.group.pins.PinsAddMethod
import com.kreait.slack.api.group.pins.PinsListMethod
import com.kreait.slack.api.group.pins.PinsMethodGroup
import com.kreait.slack.api.group.pins.PinsRemoveMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api pins method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringPinsMethodGroup : PinsMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringPinsMethodGroup::class.java)
    }

    override fun add(authToken: String): PinsAddMethod {
        return SpringPinsAddMethod(authToken)
    }

    override fun list(authToken: String): PinsListMethod {
        return SpringPinsListMethod(authToken)
    }

    override fun remove(authToken: String): PinsRemoveMethod {
        return SpringPinsRemoveMethod(authToken)
    }
}
