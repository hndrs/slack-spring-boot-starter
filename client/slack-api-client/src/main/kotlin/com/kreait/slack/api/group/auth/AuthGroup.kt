package com.kreait.slack.api.group.auth

import com.kreait.slack.api.group.auth.AuthRevokeMethod
import com.kreait.slack.api.group.auth.AuthTestMethod

interface AuthGroup {

    //TODO DOC
    fun test(authToken: String): AuthTestMethod

    //TODO DOC
    fun revoke(authToken: String): AuthRevokeMethod
}
