package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsEnableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsEnableMethod : ApiCallMethod<UsergroupsEnableMethod, SuccessfulUsergroupsEnableResponse, ErrorUsergroupsEnableResponse, SlackUsergroupsEnableRequest>()