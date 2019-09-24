package com.kreait.slack.api.spring.group.team

import com.kreait.slack.api.group.team.TeamGetProfileMethod
import com.kreait.slack.api.group.team.TeamMethodGroup

/**
 * Convenience function to apply slack api Team method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringTeamMethodGroup : TeamMethodGroup {

    override fun getProfile(authToken: String): TeamGetProfileMethod {
        return SpringTeamGetProfileMethod(authToken)
    }
}
