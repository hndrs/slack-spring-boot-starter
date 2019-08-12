package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsCreateMethod : ApiCallMethod<UsergroupsCreateMethod, SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse, SlackUsergroupsCreateRequest>()
