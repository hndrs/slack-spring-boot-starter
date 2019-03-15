package io.olaph.slack.client.test.group.auth

import io.olaph.slack.client.group.auth.AuthGroup

class MockAuthGroup : AuthGroup {

    private val mockAuthMethod = MockAuthTestMethod()
    private val mockAuthRevokeMethod = MockAuthRevokeMethod()

    override fun test(authToken: String): MockAuthTestMethod {
        return mockAuthMethod
    }

    override fun revoke(authToken: String): MockAuthRevokeMethod {
        return mockAuthRevokeMethod
    }

}
