package io.hndrs.slack.api.group.usergroups.users

import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.users.update
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUsersUpdateMethod :
    ApiCallMethod<UsergroupsUsersUpdateMethod, SuccessfulUsergroupUsersUpdateResponse,
            ErrorUsergroupUsersUpdateResponse, UsergroupUsersUpdateRequest>()
