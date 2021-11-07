package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetPresenceRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.getPresence
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersGetPresenceMethod :
    ApiCallMethod<UsersGetPresenceMethod, SuccessfulGetPresenceResponse, ErrorGetPresenceResponse, GetPresenceRequest>()
