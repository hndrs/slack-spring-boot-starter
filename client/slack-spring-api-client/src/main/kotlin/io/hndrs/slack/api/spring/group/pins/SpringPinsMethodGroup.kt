package io.hndrs.slack.api.spring.group.pins

import io.hndrs.slack.api.group.pins.PinsAddMethod
import io.hndrs.slack.api.group.pins.PinsListMethod
import io.hndrs.slack.api.group.pins.PinsMethodGroup
import io.hndrs.slack.api.group.pins.PinsRemoveMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api pins method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringPinsMethodGroup : PinsMethodGroup {

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
