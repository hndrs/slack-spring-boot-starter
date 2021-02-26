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
    fun replaceUsers(authToken: String): io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersUpdateMethod

    /**
     * Create a User Group
     */
    fun create(authToken: String): io.hndrs.slack.api.group.usergroups.UsergroupsCreateMethod

    /**
     * List all User Groups for a team
     */
    fun listGroups(authToken: String): io.hndrs.slack.api.group.usergroups.UsergroupsListMethod

    /**
     * Update an existing User Group
     */
    fun update(authToken: String): io.hndrs.slack.api.group.usergroups.UsergroupsUpdateMethod

    /**
     * Disable an existing User Group
     */
    fun disable(authToken: String): io.hndrs.slack.api.group.usergroups.UsergroupsDisableMethod

    /**
     * List all users in a User Group
     */
    fun listUsers(authToken: String): io.hndrs.slack.api.group.usergroups.users.UsergroupsUsersListMethod

    /**
     * Enable a User Group
     */
    fun enable(authToken: String): io.hndrs.slack.api.group.usergroups.UsergroupsEnableMethod
}
