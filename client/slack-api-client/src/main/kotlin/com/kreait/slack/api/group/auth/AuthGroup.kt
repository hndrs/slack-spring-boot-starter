package com.kreait.slack.api.group.auth

/**
 * Convenience function to handle the authentication token
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface AuthGroup {

    /**
     * Checks authentication & identity.
     * https://api.slack.com/methods/auth.test
     */
    fun test(authToken: String): AuthTestMethod

    /**
     * Revokes a token.
     * https://api.slack.com/methods/auth.revoke
     */
    fun revoke(authToken: String): AuthRevokeMethod
}
