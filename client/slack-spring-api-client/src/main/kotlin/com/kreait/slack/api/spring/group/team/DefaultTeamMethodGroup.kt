package com.kreait.slack.api.spring.group.team

import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.group.team.TeamMethodGroup

class DefaultTeamMethodGroup : TeamMethodGroup {

    override fun getProfile(authToken: String): TeamGetProfileMethod {
        return DefaultTeamGetProfileMethod(authToken)
    }
}
