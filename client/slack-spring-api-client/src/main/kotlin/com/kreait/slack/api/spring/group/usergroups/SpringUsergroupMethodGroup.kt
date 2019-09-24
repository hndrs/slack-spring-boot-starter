package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersListMethod
import com.kreait.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersUpdateMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api Usergroups method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringUsergroupMethodGroup : UsergroupsMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringUsergroupMethodGroup::class.java)
    }

    override fun updateUsers(authToken: String) = SpringUsergroupsUsersUpdateMethod(authToken)

    override fun create(authToken: String) = SpringUsergroupsCreateMethod(authToken)

    override fun list(authToken: String) = SpringUsergroupsListMethod(authToken)

    override fun update(authToken: String) = SpringUsergroupsUpdateMethod(authToken)

    override fun disable(authToken: String) = SpringUsergroupsDisableMethod(authToken)

    override fun listAllUsers(authToken: String) = SpringUsergroupsUsersListMethod(authToken)

    override fun enable(authToken: String) = SpringUsergroupsEnableMethod(authToken)
}
