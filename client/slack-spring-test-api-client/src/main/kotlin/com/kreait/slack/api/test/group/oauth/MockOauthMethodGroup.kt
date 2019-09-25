package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.oauth.OauthTokenMethod

/**
 * Testable implementation of [OauthMethodGroup]
 */
class MockOauthMethodGroup : OauthMethodGroup {
    private val mockOauthAccessMethod = MockOauthAccessMethod()
    override fun access(): MockOauthAccessMethod {
        return mockOauthAccessMethod
    }

    override fun token(authToken: String): OauthTokenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
