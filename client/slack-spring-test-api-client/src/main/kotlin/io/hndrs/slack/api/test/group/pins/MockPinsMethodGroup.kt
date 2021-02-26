package io.hndrs.slack.api.test.group.pins

import io.hndrs.slack.api.group.pins.PinsMethodGroup

/**
 * Testable implementation of [PinsMethodGroup]
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class MockPinsMethodGroup : io.hndrs.slack.api.group.pins.PinsMethodGroup {
    private val mockPinsAddMethod = MockPinsAddMethod()
    private val mockPinsListMethod = MockPinsListMethod()
    private val mockPinsRemoveMethod = MockPinsRemoveMethod()

    override fun add(authToken: String) = mockPinsAddMethod
    override fun list(authToken: String) = mockPinsListMethod
    override fun remove(authToken: String) = mockPinsRemoveMethod
}
