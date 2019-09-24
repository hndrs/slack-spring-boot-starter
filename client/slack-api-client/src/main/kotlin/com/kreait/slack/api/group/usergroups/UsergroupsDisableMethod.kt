package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.DisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.disable
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsDisableMethod : ApiCallMethod<UsergroupsDisableMethod, SuccessfulDisableResponse, ErrorDisableResponse, DisableRequest>()
