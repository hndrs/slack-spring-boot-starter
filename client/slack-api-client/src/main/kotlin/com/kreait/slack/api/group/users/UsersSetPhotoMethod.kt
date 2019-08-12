package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetPhotoRequest
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsersSetPhotoMethod : ApiCallMethod<UsersSetPhotoMethod, SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse, UsersSetPhotoRequest>()
