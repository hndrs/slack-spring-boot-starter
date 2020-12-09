package com.kreait.slack.api.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.users.update
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUsersUpdateMethod :
    ApiCallMethod<UsergroupsUsersUpdateMethod, SuccessfulUsergroupUsersUpdateResponse,
            ErrorUsergroupUsersUpdateResponse, UsergroupUsersUpdateRequest>()
