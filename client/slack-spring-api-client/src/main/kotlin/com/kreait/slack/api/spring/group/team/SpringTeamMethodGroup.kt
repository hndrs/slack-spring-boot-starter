package com.kreait.slack.api.spring.group.team

import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.group.team.TeamMethodGroup

class SpringTeamMethodGroup : TeamMethodGroup {

    override fun getProfile(authToken: String): TeamGetProfileMethod {
        return SpringTeamGetProfileMethod(authToken)
    }
}
