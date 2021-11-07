package io.hndrs.slack.api.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.EnableRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.enable
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsEnableMethod :
    ApiCallMethod<UsergroupsEnableMethod, SuccessfulEnableResponse, ErrorEnableResponse, EnableRequest>()
