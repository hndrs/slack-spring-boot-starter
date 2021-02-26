package io.hndrs.slack.api.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.UpdateRequest
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.update
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUpdateMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.usergroups.UsergroupsUpdateMethod, SuccessfulUpdateResponse, ErrorUpdateResponse, UpdateRequest>()
