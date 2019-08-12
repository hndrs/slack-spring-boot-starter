package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsEnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsEnableMethod : ApiCallMethod<UsergroupsEnableMethod, SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse, SlackUsergroupsEnableRequest>()