package io.hndrs.slack.api.test.group.auth

import io.hndrs.slack.api.group.auth.AuthGroup

/**
 * Mockable implementation of [AuthGroup]
 */
class MockAuthGroup : io.hndrs.slack.api.group.auth.AuthGroup {

    private val mockAuthTestMethod = MockAuthTestMethod()
    private val mockAuthRevokeMethod = MockAuthRevokeMethod()

    override fun test(authToken: String) = mockAuthTestMethod
    override fun revoke(authToken: String) = mockAuthRevokeMethod

}
