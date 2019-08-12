package com.kreait.slack.api.spring.group.oauth

import com.kreait.slack.api.spring.group.users.DefaultUserMethodGroup
import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.oauth.OauthTokenMethod
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
