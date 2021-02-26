package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetProfileRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.profile.set
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersSetProfileMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.users.UsersSetProfileMethod, SuccessfulSetProfileResponse, ErrorSetProfileResponse, SetProfileRequest>()

