package com.kreait.slack.api.spring.group.stars

import com.kreait.slack.api.group.stars.StarsAddMethod
import com.kreait.slack.api.group.stars.StarsListMethod
import com.kreait.slack.api.group.stars.StarsMethodGroup
import com.kreait.slack.api.group.stars.StarsRemoveMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api stars method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringStarsMethodGroup : StarsMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringStarsMethodGroup::class.java)
    }

    override fun add(authToken: String): StarsAddMethod {
        return SpringStarsAddMethod(authToken)
    }

    override fun list(authToken: String): StarsListMethod {
        return SpringStarsListMethod(authToken)
    }

    override fun remove(authToken: String): StarsRemoveMethod {
        return SpringStarsRemoveMethod(authToken)
    }
}
