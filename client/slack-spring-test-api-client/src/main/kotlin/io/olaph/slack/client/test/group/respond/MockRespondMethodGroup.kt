package io.olaph.slack.client.test.group.respond

import io.olaph.slack.client.group.respond.RespondMethodGroup

class MockRespondMethodGroup : RespondMethodGroup {
    private val mockRespondEphemeralMethod = MockRespondMessageMethod();

    override fun message(responseUrl: String): MockRespondMessageMethod {
        return mockRespondEphemeralMethod
    }
}