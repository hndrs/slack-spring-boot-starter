package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.group.auth.AuthGroup

class MockAuthGroup : AuthGroup {

    private val mockAuthTestMethod = MockAuthTestMethod()
    private val mockAuthRevokeMethod = MockAuthRevokeMethod()

    override fun test(authToken: String) = mockAuthTestMethod
    override fun revoke(authToken: String) = mockAuthRevokeMethod
}
