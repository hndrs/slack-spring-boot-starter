package io.olaph.slack.client.test.group.respond

import io.olaph.slack.client.group.respond.RespondMethodGroup

class MockRespondMethodGroup : RespondMethodGroup {
    private val mockRespondEphemeralMethod = MockRespondEphemeralMethod();

    override fun ephemeral(responseUrl: String): MockRespondEphemeralMethod {
        return mockRespondEphemeralMethod
    }
}