package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.test.group.usergroups.users.MockUsergroupsUsersListMethod
import com.kreait.slack.api.test.group.usergroups.users.MockUsergroupsUsersUpdateMethod

/**
 * Testable implementation of [UsergroupsMethodGroup]
 */
class MockUsergroupsMethodGroup : UsergroupsMethodGroup {
    private val mockUsergroupsUsersUpdateMethod = MockUsergroupsUsersUpdateMethod()
    private val mockUsergroupsCreateMethod = MockUsergroupsCreateMethod()
    private val mockUsergroupsListMethod = MockUsergroupsListMethod()
    private val mockUsergroupsUpdateMethod = MockUsergroupsUpdateMethod()
    private val mockUsergroupsDisableMethod = MockUsergroupsDisableMethod()
    private val mockUsergroupsUsersListMethod = MockUsergroupsUsersListMethod()
    private val mockUsergroupsEnableMethod = MockUsergroupsEnableMethod()

    override fun replaceUsers(authToken: String) = mockUsergroupsUsersUpdateMethod

    override fun create(authToken: String) = mockUsergroupsCreateMethod

    override fun listGroups(authToken: String) = mockUsergroupsListMethod

    override fun update(authToken: String) = mockUsergroupsUpdateMethod

    override fun disable(authToken: String) = mockUsergroupsDisableMethod

    override fun listUsers(authToken: String) = mockUsergroupsUsersListMethod

    override fun enable(authToken: String) = mockUsergroupsEnableMethod
}
