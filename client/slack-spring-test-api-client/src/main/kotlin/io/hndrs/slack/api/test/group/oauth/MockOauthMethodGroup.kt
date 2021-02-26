package io.hndrs.slack.api.test.group.oauth

import io.hndrs.slack.api.group.oauth.OauthMethodGroup

/**
 * Testable implementation of [OauthMethodGroup]
 */
class MockOauthMethodGroup : io.hndrs.slack.api.group.oauth.OauthMethodGroup {

    private val mockOauthAccessMethod = MockOauthAccessMethod()

    override fun access() = mockOauthAccessMethod
}
