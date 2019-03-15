package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse

@Suppress("UNCHECKED_CAST")
abstract class UserListMethod : ApiCallMethod<UserListMethod, SuccessfulUserListResponse, ErrorUserListResponse, SlackUserListRequest>()

