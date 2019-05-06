package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetProfileResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersSetProfileMethod : ApiCallMethod<UsersSetProfileMethod, SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse, Unit>()

