package io.hndrs.slack.api.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.CreateRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.create
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsCreateMethod :
    ApiCallMethod<UsergroupsCreateMethod, SuccessfulCreateResponse, ErrorCreateResponse, CreateRequest>()
