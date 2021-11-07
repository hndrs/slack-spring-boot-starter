package io.hndrs.slack.api.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.DisableRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.disable
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsDisableMethod :
    ApiCallMethod<UsergroupsDisableMethod, SuccessfulDisableResponse, ErrorDisableResponse, DisableRequest>()
