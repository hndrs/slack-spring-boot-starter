package io.hndrs.slack.api.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ListRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.list
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsListMethod :
    ApiCallMethod<UsergroupsListMethod, SuccessfulListResponse, ErrorListResponse, ListRequest>()

