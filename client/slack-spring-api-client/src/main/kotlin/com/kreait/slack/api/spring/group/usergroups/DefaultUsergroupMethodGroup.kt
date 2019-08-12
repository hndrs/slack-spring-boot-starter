package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.spring.group.usergroups.users.DefaultUsergroupsUsersListMethod
import com.kreait.slack.api.spring.group.usergroups.users.DefaultUsergroupsUsersUpdateMethod
import org.slf4j.LoggerFactory

class DefaultUsergroupMethodGroup : UsergroupsMethodGroup {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultUsergroupMethodGroup::class.java)
    }

    override fun usersUpdate(authToken: String) = DefaultUsergroupsUsersUpdateMethod(authToken)

    override fun create(authToken: String) = DefaultUsergroupsCreateMethod(authToken)

    override fun list(authToken: String) = DefaultUsergroupsListMethod(authToken)

    override fun update(authToken: String) = DefaultUsergroupsUpdateMethod(authToken)

    override fun disable(authToken: String) = DefaultUsergroupsDisableMethod(authToken)

    override fun usersList(authToken: String) = DefaultUsergroupsUsersListMethod(authToken)

    override fun enable(authToken: String) = DefaultUsergroupsEnableMethod(authToken)
}
