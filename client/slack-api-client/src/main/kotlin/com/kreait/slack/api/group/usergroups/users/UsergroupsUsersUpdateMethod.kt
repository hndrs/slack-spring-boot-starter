package com.kreait.slack.api.group.usergroups.users

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUsersUpdateMethod : ApiCallMethod<UsergroupsUsersUpdateMethod, SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse, SlackUsergroupUsersUpdateRequest>()
