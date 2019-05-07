package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersSetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPresenceResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersSetPresenceMethod : ApiCallMethod<UsersSetPresenceMethod, SuccessfulUsersSetPresenceResponse, ErrorUsersSetPresenceResponse, SlackUsersSetPresenceRequest>()