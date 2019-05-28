package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsUpdateMethod : ApiCallMethod<UsergroupsUpdateMethod, SuccessfulUsergroupsUpdateResponse, ErrorUsergroupsUpdateResponse, SlackUsergroupsUpdateRequest>()