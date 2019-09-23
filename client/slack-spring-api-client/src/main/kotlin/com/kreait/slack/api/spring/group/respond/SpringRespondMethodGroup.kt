package com.kreait.slack.api.spring.group.respond

import com.kreait.slack.api.group.respond.RespondMessageMethod
import com.kreait.slack.api.group.respond.RespondMethodGroup


class SpringRespondMethodGroup : RespondMethodGroup {
    override fun message(responseUrl: String): RespondMessageMethod {
        return SpringRespondMessageMethod(responseUrl)
    }
}
