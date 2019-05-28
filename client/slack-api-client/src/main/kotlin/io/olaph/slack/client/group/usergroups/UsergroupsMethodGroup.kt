package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersListMethod
import io.olaph.slack.client.group.usergroups.users.UsergroupsUsersUpdateMethod

interface UsergroupsMethodGroup {

    //TODO create subgroup
    fun usersUpdate(authToken: String): UsergroupsUsersUpdateMethod

    fun create(authToken: String): UsergroupsCreateMethod

    fun list(authToken: String): UsergroupsListMethod
    
    fun update(authToken: String): UsergroupsUpdateMethod

    fun disable(authToken: String): UsergroupsDisableMethod

    fun usersList(authToken: String): UsergroupsUsersListMethod
}