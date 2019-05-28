package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsDisableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsDisableMethod : ApiCallMethod<UsergroupsDisableMethod, SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse, SlackUsergroupsDisableRequest>()