package io.hndrs.slack.api.spring.group.auth

import io.hndrs.slack.api.group.auth.AuthGroup
import io.hndrs.slack.api.group.auth.AuthRevokeMethod
import io.hndrs.slack.api.group.auth.AuthTestMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to handle the authentication token
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringAuthMethodGroup : AuthGroup {

    override fun revoke(authToken: String): AuthRevokeMethod {
        return SpringRevokeMethod(authToken)
    }

    override fun test(authToken: String): AuthTestMethod {
        return SpringTestMethod(authToken)
    }
}
