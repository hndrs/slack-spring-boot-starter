package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetPresenceRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.setPresence
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersSetPresenceMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.users.UsersSetPresenceMethod, SuccessfulSetPresenceResponse, ErrorSetPresenceResponse, SetPresenceRequest>()
