package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.deletePhoto
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersDeletePhotoMethod :
    ApiCallMethod<UsersDeletePhotoMethod, SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse, Unit>()

