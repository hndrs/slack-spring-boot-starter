package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersUpdateMethod

interface UsergroupsMethodGroup {

    fun usersUpdate(authToken: String): UsergroupsUsersUpdateMethod
}