package com.kreait.slack.api.spring.group.auth

import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.auth.AuthRevokeMethod
import com.kreait.slack.api.group.auth.AuthTestMethod
import org.slf4j.LoggerFactory

class DefaultAuthMethodGroup : AuthGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultAuthMethodGroup::class.java)
    }

    override fun revoke(authToken: String): AuthRevokeMethod {
        return DefaultRevokeMethod(authToken)
    }

    override fun test(authToken: String): AuthTestMethod {
        return DefaultTestMethod(authToken)
    }
}
