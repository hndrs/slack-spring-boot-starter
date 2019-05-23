package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersUpdateMethod

interface UsergroupsMethodGroup {

    //TODO create subgroup
    fun usersUpdate(authToken: String): UsergroupsUsersUpdateMethod

    fun create(authToken: String): UsergroupsCreateMethod
}