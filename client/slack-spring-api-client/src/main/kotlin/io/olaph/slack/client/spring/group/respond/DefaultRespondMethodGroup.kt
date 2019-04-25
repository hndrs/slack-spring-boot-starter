package io.olaph.slack.client.spring.group.respond

import io.olaph.slack.client.group.respond.RespondEphemeralMethod
import io.olaph.slack.client.group.respond.RespondMethodGroup


class DefaultRespondMethodGroup : RespondMethodGroup {
    override fun ephemeral(responseUrl: String): RespondEphemeralMethod {
        return DefaultRespondEphemeralMethod(responseUrl)
    }
}