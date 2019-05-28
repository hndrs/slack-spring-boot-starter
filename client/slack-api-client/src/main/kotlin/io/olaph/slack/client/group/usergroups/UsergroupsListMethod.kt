package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsListRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsListResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsListMethod : ApiCallMethod<UsergroupsListMethod, SuccessfulUsergroupsListResponse, ErrorUsergroupsListResponse, SlackUsergroupsListRequest>()