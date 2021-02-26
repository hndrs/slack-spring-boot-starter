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
class SpringAuthMethodGroup : io.hndrs.slack.api.group.auth.AuthGroup {

    override fun revoke(authToken: String): io.hndrs.slack.api.group.auth.AuthRevokeMethod {
        return SpringRevokeMethod(authToken)
    }

    override fun test(authToken: String): io.hndrs.slack.api.group.auth.AuthTestMethod {
        return SpringTestMethod(authToken)
    }
}
