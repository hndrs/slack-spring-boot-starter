package com.kreait.slack.api.spring.group.oauth

import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.oauth.OauthTokenMethod
import com.kreait.slack.api.spring.group.users.SpringUserMethodGroup
import org.slf4j.LoggerFactory

class SpringOauthMethodGroup : OauthMethodGroup {
    override fun token(authToken: String): OauthTokenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val LOG = LoggerFactory.getLogger(SpringUserMethodGroup::class.java)
    }

    override fun access(): SpringOauthAccessMethod {
        return SpringOauthAccessMethod()
    }

}
