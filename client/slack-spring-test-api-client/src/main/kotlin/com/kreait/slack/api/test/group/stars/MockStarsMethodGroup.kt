package com.kreait.slack.api.test.group.stars

import com.kreait.slack.api.group.stars.StarsMethodGroup

/**
 * Testable implementation of [StarsMethodGroup]
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class MockStarsMethodGroup : StarsMethodGroup {
    private val mockStarsAddMethod = MockStarsAddMethod()
    private val mockStarsListMethod = MockStarsListMethod()
    private val mockStarsRemoveMethod = MockStarsRemoveMethod()

    override fun add(authToken: String) = mockStarsAddMethod
    override fun list(authToken: String) = mockStarsListMethod
    override fun remove(authToken: String) = mockStarsRemoveMethod
}
