package com.kreait.slack.api.group.auth

interface AuthGroup {

    //TODO DOC
    fun test(authToken: String): AuthTestMethod

    //TODO DOC
    fun revoke(authToken: String): AuthRevokeMethod
}
