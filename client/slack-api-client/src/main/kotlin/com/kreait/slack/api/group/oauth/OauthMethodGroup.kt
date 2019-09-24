package com.kreait.slack.api.group.oauth

/**
 * Convenience class to handle the oauth operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface OauthMethodGroup {
    /**Exchanges a temporary OAuth verifier code for an access token.*/
    fun access(): OauthAccessMethod

    /**Exchanges a temporary OAuth verifier code for a workspace token*/
    fun token(authToken: String): OauthTokenMethod
}
