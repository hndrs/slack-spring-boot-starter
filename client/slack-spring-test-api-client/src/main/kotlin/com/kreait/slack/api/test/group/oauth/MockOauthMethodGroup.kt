package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.oauth.OauthTokenMethod

class MockOauthMethodGroup : OauthMethodGroup {

    private val mockOauthAccessMethod = MockOauthAccessMethod()

    override fun access() = mockOauthAccessMethod

    override fun token(authToken: String): OauthTokenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
