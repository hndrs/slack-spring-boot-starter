package com.kreait.slack.api.group.usergroups.users

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse

@Suppress("UNCHECKED_CASt")
abstract class UsergroupsUsersListMethod : ApiCallMethod<UsergroupsUsersListMethod, SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse, SlackUsergroupsUsersListRequest>()
