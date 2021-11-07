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
class SpringStarsMethodGroup : StarsMethodGroup {

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
