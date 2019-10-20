package com.kreait.slack.api.test.group.pins

import com.kreait.slack.api.group.pins.PinsMethodGroup

/**
 * Testable implementation of [PinsMethodGroup]
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class MockPinsMethodGroup : PinsMethodGroup {
    private val mockPinsAddMethod = MockPinsAddMethod()
    private val mockPinsListMethod = MockPinsListMethod()
    private val mockPinsRemoveMethod = MockPinsRemoveMethod()

    override fun add(authToken: String) = mockPinsAddMethod
    override fun list(authToken: String) = mockPinsListMethod
    override fun remove(authToken: String) = mockPinsRemoveMethod
}
