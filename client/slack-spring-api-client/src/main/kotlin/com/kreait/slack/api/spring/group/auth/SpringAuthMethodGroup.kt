package com.kreait.slack.api.spring.group.auth

import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.auth.AuthRevokeMethod
import com.kreait.slack.api.group.auth.AuthTestMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to handle the authentication token
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringAuthMethodGroup : AuthGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringAuthMethodGroup::class.java)
    }

    override fun revoke(authToken: String): AuthRevokeMethod {
        return SpringRevokeMethod(authToken)
    }

    override fun test(authToken: String): AuthTestMethod {
        return SpringTestMethod(authToken)
    }
}
