package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.*

@Suppress("UNCHECKED_CAST")
abstract class UsersGetProfileMethod : ApiCallMethod<UsersGetProfileMethod, SuccessfulUsersGetProfileResponse, ErrorUsersGetProfileResponse, UsersGetProfileRequest>()

