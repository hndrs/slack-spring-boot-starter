package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsListResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsListMethod : ApiCallMethod<UsergroupsListMethod, SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse, SlackUsergroupsListRequest>()
