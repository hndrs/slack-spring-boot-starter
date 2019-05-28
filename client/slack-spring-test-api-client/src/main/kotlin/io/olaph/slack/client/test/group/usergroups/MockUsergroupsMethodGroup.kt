package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.usergroups.UsergroupsMethodGroup
import io.olaph.slack.client.test.group.usergroups.users.MockUsergroupsUsersListMethod
import io.olaph.slack.client.test.group.usergroups.users.MockUsergroupsUsersUpdateMethod

class MockUsergroupsMethodGroup : UsergroupsMethodGroup {
    private val mockUsergroupsUsersUpdateMethod = MockUsergroupsUsersUpdateMethod()
    private val mockUsergroupsCreateMethod = MockUsergroupsCreateMethod()
    private val mockUsergroupsListMethod = MockUsergroupsListMethod()
    private val mockUsergroupsUpdateMethod = MockUsergroupsUpdateMethod()
    private val mockUsergroupsDisableMethod = MockUsergroupsDisableMethod()
    private val mockUsergroupsUsersListMethod = MockUsergroupsUsersListMethod()

    override fun usersUpdate(authToken: String) = mockUsergroupsUsersUpdateMethod

    override fun create(authToken: String) = mockUsergroupsCreateMethod

    override fun list(authToken: String) = mockUsergroupsListMethod

    override fun update(authToken: String) = mockUsergroupsUpdateMethod

    override fun disable(authToken: String) = mockUsergroupsDisableMethod

    override fun usersList(authToken: String) = mockUsergroupsUsersListMethod
}