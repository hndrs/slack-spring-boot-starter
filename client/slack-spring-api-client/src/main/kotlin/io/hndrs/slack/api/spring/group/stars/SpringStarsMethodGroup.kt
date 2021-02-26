package io.hndrs.slack.api.spring.group.stars

import io.hndrs.slack.api.group.stars.StarsAddMethod
import io.hndrs.slack.api.group.stars.StarsListMethod
import io.hndrs.slack.api.group.stars.StarsMethodGroup
import io.hndrs.slack.api.group.stars.StarsRemoveMethod

/**
 * Convenience function to apply slack api stars method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringStarsMethodGroup : io.hndrs.slack.api.group.stars.StarsMethodGroup {

    override fun add(authToken: String): io.hndrs.slack.api.group.stars.StarsAddMethod {
        return SpringStarsAddMethod(authToken)
    }

    override fun list(authToken: String): io.hndrs.slack.api.group.stars.StarsListMethod {
        return SpringStarsListMethod(authToken)
    }

    override fun remove(authToken: String): io.hndrs.slack.api.group.stars.StarsRemoveMethod {
        return SpringStarsRemoveMethod(authToken)
    }
}
