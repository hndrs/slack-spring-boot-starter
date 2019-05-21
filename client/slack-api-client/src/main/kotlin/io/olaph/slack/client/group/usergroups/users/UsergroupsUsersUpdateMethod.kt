package io.olaph.slack.client.group.usergroups.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUsersUpdateMethod : ApiCallMethod<UsergroupsUsersUpdateMethod, SuccessfulUsergroupUsersUpdateResponse, ErrorUsergroupUsersUpdateResponse, SlackUsergroupUsersUpdateRequest>()