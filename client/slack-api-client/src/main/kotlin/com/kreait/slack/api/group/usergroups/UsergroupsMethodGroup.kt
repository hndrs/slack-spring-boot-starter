package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.group.usergroups.users.UsergroupsUsersListMethod
import com.kreait.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod

interface UsergroupsMethodGroup {

    //TODO create subgroup
    fun usersUpdate(authToken: String): UsergroupsUsersUpdateMethod

    fun create(authToken: String): UsergroupsCreateMethod

    fun list(authToken: String): UsergroupsListMethod

    fun update(authToken: String): UsergroupsUpdateMethod

    fun disable(authToken: String): UsergroupsDisableMethod

    fun usersList(authToken: String): UsergroupsUsersListMethod

    fun enable(authToken: String): UsergroupsEnableMethod
}
