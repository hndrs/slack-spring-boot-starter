package io.hndrs.slack.api.spring.group.usergroups

import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersListMethod
import io.hndrs.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersUpdateMethod

/**
 * Convenience function to apply slack api Usergroups method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringUsergroupMethodGroup : io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup {

    override fun replaceUsers(authToken: String) = SpringUsergroupsUsersUpdateMethod(authToken)

    override fun create(authToken: String) = SpringUsergroupsCreateMethod(authToken)

    override fun listGroups(authToken: String) = SpringUsergroupsListMethod(authToken)

    override fun update(authToken: String) = SpringUsergroupsUpdateMethod(authToken)

    override fun disable(authToken: String) = SpringUsergroupsDisableMethod(authToken)

    override fun listUsers(authToken: String) = SpringUsergroupsUsersListMethod(authToken)

    override fun enable(authToken: String) = SpringUsergroupsEnableMethod(authToken)
}
