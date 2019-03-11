package io.olaph.slack.client.implementation.group.auth

import io.olaph.slack.client.group.auth.AuthGroup
import io.olaph.slack.client.group.auth.AuthTestMethod
import io.olaph.slack.client.group.auth.RevokeMethod
import org.slf4j.LoggerFactory

class DefaultAuthMethodGroup : AuthGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultAuthMethodGroup::class.java)
    }

    override fun revoke(authToken: String): RevokeMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun test(authToken: String): AuthTestMethod {
        return DefaultTestMethod(authToken)
    }
}
