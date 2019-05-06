package io.olaph.slack.client.spring.group.team

import io.olaph.slack.client.group.team.TeamGetProfileMethod
import io.olaph.slack.client.group.team.TeamMethodGroup

class DefaultTeamMethodGroup : TeamMethodGroup {

    override fun getProfile(authToken: String): TeamGetProfileMethod {
        return DefaultTeamGetProfileMethod(authToken)
    }
}