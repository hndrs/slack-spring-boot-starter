package io.olaph.slack.client.spring.group.auth

import io.olaph.slack.client.group.auth.AuthGroup
import io.olaph.slack.client.group.auth.AuthRevokeMethod
import io.olaph.slack.client.group.auth.AuthTestMethod
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
