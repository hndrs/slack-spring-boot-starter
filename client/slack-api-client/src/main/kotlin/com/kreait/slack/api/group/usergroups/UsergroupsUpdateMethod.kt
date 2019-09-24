package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UpdateRequest
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.update
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUpdateMethod : ApiCallMethod<UsergroupsUpdateMethod, SuccessfulUpdateResponse, ErrorUpdateResponse, UpdateRequest>()
