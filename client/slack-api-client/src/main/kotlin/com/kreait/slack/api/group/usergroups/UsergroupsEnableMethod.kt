package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.EnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.enable
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsEnableMethod : ApiCallMethod<UsergroupsEnableMethod, SuccessfulEnableResponse, ErrorEnableResponse, EnableRequest>()
