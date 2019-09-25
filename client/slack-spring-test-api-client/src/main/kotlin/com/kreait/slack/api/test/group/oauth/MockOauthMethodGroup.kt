package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.group.oauth.OauthMethodGroup

/**
 * Testable implementation of [OauthMethodGroup]
 */
class MockOauthMethodGroup : OauthMethodGroup {

    private val mockOauthAccessMethod = MockOauthAccessMethod()

    override fun access() = mockOauthAccessMethod
}
