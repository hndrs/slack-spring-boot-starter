package io.olaph.slack.client.spring.group.oauth

import io.olaph.slack.client.spring.group.users.DefaultUserMethodGroup
import io.olaph.slack.client.group.oauth.OauthMethodGroup
import io.olaph.slack.client.group.oauth.OauthTokenMethod
import org.slf4j.LoggerFactory

class DefaultOauthMethodGroup : OauthMethodGroup {
    override fun token(authToken: String): OauthTokenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultUserMethodGroup::class.java)
    }

    override fun access(): DefaultOauthAccessMethod {
        return DefaultOauthAccessMethod()
    }

}
