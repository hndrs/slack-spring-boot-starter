package com.kreait.slack.api.test.group.respond

import com.kreait.slack.api.group.respond.RespondMethodGroup

class MockRespondMethodGroup : RespondMethodGroup {

    private val mockRespondEphemeralMethod = MockRespondMessageMethod();

    override fun message(responseUrl: String) = mockRespondEphemeralMethod
}
