package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetProfileRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.profile.get
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersGetProfileMethod :
    ApiCallMethod<UsersGetProfileMethod, SuccessfulGetProfileResponse, ErrorGetProfileResponse, GetProfileRequest>()

