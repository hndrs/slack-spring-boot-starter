package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.usergroups.UsergroupsMethodGroup
import io.olaph.slack.client.test.group.usergroups.users.MockUsergroupsUsersUpdateMethod

class MockUsergroupsMethodGroup : UsergroupsMethodGroup {
    private val mockUsergroupsUsersUpdateMethod = MockUsergroupsUsersUpdateMethod()
    private val mockUsergroupsCreateMethod = MockUsergroupsCreateMethod()
    private val mockUsergroupsListMethod = MockUsergroupsListMethod()
    private val mockUsergroupsUpdateMethod = MockUsergroupsUpdateMethod()

    override fun usersUpdate(authToken: String) = mockUsergroupsUsersUpdateMethod

    override fun create(authToken: String) = mockUsergroupsCreateMethod

    override fun list(authToken: String) = mockUsergroupsListMethod

    override fun update(authToken: String) = mockUsergroupsUpdateMethod
}