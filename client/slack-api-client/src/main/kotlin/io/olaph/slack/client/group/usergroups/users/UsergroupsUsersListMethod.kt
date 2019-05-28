package io.olaph.slack.client.group.usergroups.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse

@Suppress("UNCHECKED_CASt")
abstract class UsergroupsUsersListMethod : ApiCallMethod<UsergroupsUsersListMethod, SuccessfulUsergroupsUsersListResponse, ErrorUsergroupsUsersListResponse, SlackUsergroupsUsersListRequest>()