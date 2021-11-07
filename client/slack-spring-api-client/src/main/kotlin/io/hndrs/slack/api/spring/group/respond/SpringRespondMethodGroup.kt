package io.hndrs.slack.api.spring.group.respond

import io.hndrs.slack.api.group.respond.RespondMessageMethod
import io.hndrs.slack.api.group.respond.RespondMethodGroup

/**
 * Convenience function to reply to slack interactions
 *
 * [Slack Api Documentation](https://api.slack.com/slash-commands#responding_response_url)
 */
class SpringRespondMethodGroup : RespondMethodGroup {
    override fun message(responseUrl: String): RespondMessageMethod {
        return SpringRespondMessageMethod(responseUrl)
    }
}
