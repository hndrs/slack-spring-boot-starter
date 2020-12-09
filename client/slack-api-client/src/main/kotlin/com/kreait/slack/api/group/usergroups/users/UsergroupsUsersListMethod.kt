package com.kreait.slack.api.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListRequest
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.users.list
 */
@Suppress("UNCHECKED_CASt")
abstract class UsergroupsUsersListMethod :
    ApiCallMethod<UsergroupsUsersListMethod, SuccessfulUsergroupsUsersListResponse,
            ErrorUsergroupsUsersListResponse, UsergroupsUsersListRequest>()
