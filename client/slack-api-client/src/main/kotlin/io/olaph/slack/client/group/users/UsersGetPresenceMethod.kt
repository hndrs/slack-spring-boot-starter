package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetPresenceResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersGetPresenceMethod : ApiCallMethod<UsersGetPresenceMethod, SuccessfulUsersGetPresenceResponse, ErrorUsersGetPresenceResponse, UsersGetPresenceRequest>()
