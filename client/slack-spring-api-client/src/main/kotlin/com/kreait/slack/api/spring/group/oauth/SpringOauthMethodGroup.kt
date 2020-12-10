package com.kreait.slack.api.spring.group.oauth

import com.kreait.slack.api.group.oauth.OauthMethodGroup

/**
 * Convenience function to apply slack api oauth method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringOauthMethodGroup : OauthMethodGroup {

    override fun access(): SpringOauthAccessMethod {
        return SpringOauthAccessMethod()
    }
}
