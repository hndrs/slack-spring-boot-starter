package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorInfoResponse
import io.hndrs.slack.api.contract.jackson.group.users.InfoRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulInfoResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.info
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersInfoMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.users.UsersInfoMethod, SuccessfulInfoResponse, ErrorInfoResponse, InfoRequest>()

