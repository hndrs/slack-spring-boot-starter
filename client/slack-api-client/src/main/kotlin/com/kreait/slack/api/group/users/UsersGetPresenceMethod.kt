package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.getPresence
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersGetPresenceMethod :
    ApiCallMethod<UsersGetPresenceMethod, SuccessfulGetPresenceResponse, ErrorGetPresenceResponse, GetPresenceRequest>()
