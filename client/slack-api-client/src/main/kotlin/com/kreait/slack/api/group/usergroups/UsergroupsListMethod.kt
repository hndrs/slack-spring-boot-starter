package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.list
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsListMethod : ApiCallMethod<UsergroupsListMethod, SuccessfulListResponse, ErrorListResponse, ListRequest>()

