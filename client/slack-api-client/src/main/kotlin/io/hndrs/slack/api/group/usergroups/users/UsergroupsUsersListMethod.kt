package io.hndrs.slack.api.group.usergroups.users

import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListRequest
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.users.list
 */
@Suppress("UNCHECKED_CASt")
abstract class UsergroupsUsersListMethod :
    ApiCallMethod<UsergroupsUsersListMethod, SuccessfulUsergroupsUsersListResponse,
            ErrorUsergroupsUsersListResponse, UsergroupsUsersListRequest>()
