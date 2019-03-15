package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserInfoRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersInfoResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersInfoMethod : ApiCallMethod<UsersInfoMethod, SuccessfulUsersInfoResponse, ErrorUsersInfoResponse, SlackUserInfoRequest>()

