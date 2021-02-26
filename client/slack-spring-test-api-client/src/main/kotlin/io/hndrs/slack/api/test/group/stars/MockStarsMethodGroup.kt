package io.hndrs.slack.api.test.group.stars

import io.hndrs.slack.api.group.stars.StarsMethodGroup

/**
 * Testable implementation of [StarsMethodGroup]
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class MockStarsMethodGroup : io.hndrs.slack.api.group.stars.StarsMethodGroup {
    private val mockStarsAddMethod = MockStarsAddMethod()
    private val mockStarsListMethod = MockStarsListMethod()
    private val mockStarsRemoveMethod = MockStarsRemoveMethod()

    override fun add(authToken: String) = mockStarsAddMethod
    override fun list(authToken: String) = mockStarsListMethod
    override fun remove(authToken: String) = mockStarsRemoveMethod
}
