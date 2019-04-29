package io.olaph.slack.client.spring.group.respond

import io.olaph.slack.client.group.respond.RespondMessageMethod
import io.olaph.slack.client.group.respond.RespondMethodGroup


class DefaultRespondMethodGroup : RespondMethodGroup {
    override fun message(responseUrl: String): RespondMessageMethod {
        return DefaultRespondMessageMethod(responseUrl)
    }
}