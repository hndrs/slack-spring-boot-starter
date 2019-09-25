package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.group.auth.AuthGroup

/**
 * Mockable implementation of [AuthGroup]
 *
 */
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
