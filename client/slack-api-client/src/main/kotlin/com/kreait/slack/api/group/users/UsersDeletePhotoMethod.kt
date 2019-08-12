package com.kreait.slack.api.group.users

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersDeletePhotoResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersDeletePhotoMethod : ApiCallMethod<UsersDeletePhotoMethod, SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse, Unit>()

