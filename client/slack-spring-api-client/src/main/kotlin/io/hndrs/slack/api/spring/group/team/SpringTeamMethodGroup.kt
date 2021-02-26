package io.hndrs.slack.api.spring.group.team

import io.hndrs.slack.api.group.team.TeamGetProfileMethod
import io.hndrs.slack.api.group.team.TeamMethodGroup

/**
 * Convenience function to apply slack api Team method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringTeamMethodGroup : io.hndrs.slack.api.group.team.TeamMethodGroup {

    override fun getProfile(authToken: String): io.hndrs.slack.api.group.team.TeamGetProfileMethod {
        return SpringTeamGetProfileMethod(authToken)
    }
}
