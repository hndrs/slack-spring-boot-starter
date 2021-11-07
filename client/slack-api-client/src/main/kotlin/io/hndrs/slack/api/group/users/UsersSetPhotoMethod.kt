package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SetPhotoRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.setPhoto
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersSetPhotoMethod :
    ApiCallMethod<UsersSetPhotoMethod, SuccessfulSetPhotoResponse, ErrorSetPhotoResponse, SetPhotoRequest>()
