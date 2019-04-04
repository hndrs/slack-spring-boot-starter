package io.olaph.slack.client.test.group.oauth

import io.olaph.slack.client.group.oauth.OauthMethodGroup
import io.olaph.slack.client.group.oauth.OauthTokenMethod

class MockOauthMethodGroup : OauthMethodGroup {
    private val mockOauthAccessMethod = MockOauthAccessMethod()
    override fun access(): MockOauthAccessMethod {
        return mockOauthAccessMethod
    }

    override fun token(authToken: String): OauthTokenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}