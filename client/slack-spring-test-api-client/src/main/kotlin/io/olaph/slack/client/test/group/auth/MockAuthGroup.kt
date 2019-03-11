package io.olaph.slack.client.test.group.auth

import io.olaph.slack.client.group.auth.AuthGroup

class MockAuthGroup : AuthGroup {

    override fun test(authToken: String): MockAuthTestMethod {
        return MockAuthTestMethod()
    }

    override fun revoke(authToken: String): MockRevokeMethod {
        return MockRevokeMethod()
    }

}
