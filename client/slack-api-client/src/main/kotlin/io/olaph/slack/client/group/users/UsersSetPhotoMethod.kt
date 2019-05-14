package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetPhotoRequest

@Suppress("UNCHECKED_CAST")
abstract class UsersSetPhotoMethod : ApiCallMethod<UsersSetPhotoMethod, SuccessfulUsersSetPhotoResponse, ErrorUsersSetPhotoResponse, UsersSetPhotoRequest>()