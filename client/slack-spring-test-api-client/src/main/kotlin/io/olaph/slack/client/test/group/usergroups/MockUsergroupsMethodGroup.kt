package io.olaph.slack.client.test.group.usergroups

import io.olaph.slack.client.group.usergroups.UsergroupsMethodGroup
import io.olaph.slack.client.test.group.usergroups.users.MockUsergroupsUsersUpdateMethod

class MockUsergroupsMethodGroup : UsergroupsMethodGroup {

    private val mockUsergroupsUsersUpdateMethod = MockUsergroupsUsersUpdateMethod()

    override fun usersUpdate(authToken: String) = mockUsergroupsUsersUpdateMethod
}