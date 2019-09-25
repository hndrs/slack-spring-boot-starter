package com.kreait.slack.api.test.group.respond

import com.kreait.slack.api.group.respond.RespondMethodGroup

/**
 * Testable implementation of [RespondMethodGroup]
 */
class MockRespondMethodGroup : RespondMethodGroup {
    private val mockRespondEphemeralMethod = MockRespondMessageMethod();

    override fun message(responseUrl: String): MockRespondMessageMethod {
        return mockRespondEphemeralMethod
    }
}
