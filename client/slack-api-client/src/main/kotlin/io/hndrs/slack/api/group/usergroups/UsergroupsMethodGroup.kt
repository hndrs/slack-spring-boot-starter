package io.hndrs.slack.api.group.usergroups

import io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersListMethod
import io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod

/**
 * Convenience class to handle the usergroup operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface UsergroupsMethodGroup {

    /**
     * Update the list of users for a User Group
     */
    fun replaceUsers(authToken: String): UsergroupsUsersUpdateMethod

    /**
     * Create a User Group
     */
    fun create(authToken: String): UsergroupsCreateMethod

    /**
     * List all User Groups for a team
     */
    fun listGroups(authToken: String): UsergroupsListMethod

    /**
     * Update an existing User Group
     */
    fun update(authToken: String): UsergroupsUpdateMethod

    /**
     * Disable an existing User Group
     */
    fun disable(authToken: String): UsergroupsDisableMethod

    /**
     * List all users in a User Group
     */
    fun listUsers(authToken: String): UsergroupsUsersListMethod

    /**
     * Enable a User Group
     */
    fun enable(authToken: String): UsergroupsEnableMethod
}
