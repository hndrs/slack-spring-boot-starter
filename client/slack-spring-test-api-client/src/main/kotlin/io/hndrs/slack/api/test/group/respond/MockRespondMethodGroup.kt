package io.hndrs.slack.api.test.group.respond

import io.hndrs.slack.api.group.respond.RespondMethodGroup

/**
 * Testable implementation of [RespondMethodGroup]
 */
class MockRespondMethodGroup : io.hndrs.slack.api.group.respond.RespondMethodGroup {

    private val mockRespondEphemeralMethod = MockRespondMessageMethod();

    override fun message(responseUrl: String) = mockRespondEphemeralMethod
}
