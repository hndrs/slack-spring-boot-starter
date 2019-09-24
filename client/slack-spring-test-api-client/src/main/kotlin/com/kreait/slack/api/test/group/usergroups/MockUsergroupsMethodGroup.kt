package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.test.group.usergroups.users.MockUsergroupsUsersListMethod
import com.kreait.slack.api.test.group.usergroups.users.MockUsergroupsUsersUpdateMethod

class MockUsergroupsMethodGroup : UsergroupsMethodGroup {
    private val mockUsergroupsUsersUpdateMethod = MockUsergroupsUsersUpdateMethod()
    private val mockUsergroupsCreateMethod = MockUsergroupsCreateMethod()
    private val mockUsergroupsListMethod = MockUsergroupsListMethod()
    private val mockUsergroupsUpdateMethod = MockUsergroupsUpdateMethod()
    private val mockUsergroupsDisableMethod = MockUsergroupsDisableMethod()
    private val mockUsergroupsUsersListMethod = MockUsergroupsUsersListMethod()
    private val mockUsergroupsEnableMethod = MockUsergroupsEnableMethod()

    override fun updateUsers(authToken: String) = mockUsergroupsUsersUpdateMethod

    override fun create(authToken: String) = mockUsergroupsCreateMethod

    override fun list(authToken: String) = mockUsergroupsListMethod

    override fun update(authToken: String) = mockUsergroupsUpdateMethod

    override fun disable(authToken: String) = mockUsergroupsDisableMethod

    override fun listAllUsers(authToken: String) = mockUsergroupsUsersListMethod

    override fun enable(authToken: String) = mockUsergroupsEnableMethod
}
