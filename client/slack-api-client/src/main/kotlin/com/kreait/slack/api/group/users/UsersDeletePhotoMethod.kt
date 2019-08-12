package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersDeletePhotoMethod : ApiCallMethod<UsersDeletePhotoMethod, SuccessfulDeletePhotoResponse, ErrorDeletePhotoResponse, Unit>()

