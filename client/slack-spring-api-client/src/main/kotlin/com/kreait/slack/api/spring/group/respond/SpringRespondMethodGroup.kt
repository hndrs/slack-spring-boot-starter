package com.kreait.slack.api.spring.group.respond

import com.kreait.slack.api.group.respond.RespondMessageMethod
import com.kreait.slack.api.group.respond.RespondMethodGroup

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
