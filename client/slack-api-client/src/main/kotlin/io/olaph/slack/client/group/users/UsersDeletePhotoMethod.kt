package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.ErrorUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserConversationListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersDeletePhotoResponse

@Suppress("UNCHECKED_CAST")
abstract class UsersDeletePhotoMethod : ApiCallMethod<UsersDeletePhotoMethod, SuccessfulUsersDeletePhotoResponse, ErrorUsersDeletePhotoResponse, Unit>()

