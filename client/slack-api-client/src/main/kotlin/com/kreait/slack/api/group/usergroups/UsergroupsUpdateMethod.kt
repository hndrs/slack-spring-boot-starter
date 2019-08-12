package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUpdateMethod : ApiCallMethod<UsergroupsUpdateMethod, SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse, SlackUsergroupsUpdateRequest>()
