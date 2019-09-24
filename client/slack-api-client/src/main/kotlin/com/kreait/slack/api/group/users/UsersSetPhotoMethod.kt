package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersSetPhotoMethod : ApiCallMethod<UsersSetPhotoMethod, SuccessfulSetPhotoResponse, ErrorSetPhotoResponse, SetPhotoRequest>()
