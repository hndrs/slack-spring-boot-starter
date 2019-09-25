package com.kreait.slack.api.spring.group.oauth

import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.spring.group.users.SpringUserMethodGroup
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api oauth method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringOauthMethodGroup : OauthMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringUserMethodGroup::class.java)
    }

    override fun access(): SpringOauthAccessMethod {
        return SpringOauthAccessMethod()
    }
}
