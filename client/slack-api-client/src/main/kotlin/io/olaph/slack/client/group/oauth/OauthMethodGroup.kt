package io.olaph.slack.client.group.oauth

interface OauthMethodGroup {
    /**Exchanges a temporary OAuth verifier code for an access token.*/
    fun access(): OauthAccessMethod

    /**Exchanges a temporary OAuth verifier code for a workspace token*/
    fun token(authToken: String): OauthTokenMethod
}
