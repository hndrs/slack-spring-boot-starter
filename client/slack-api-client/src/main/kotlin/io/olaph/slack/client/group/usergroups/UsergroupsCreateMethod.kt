package io.olaph.slack.client.group.usergroups

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsCreateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsCreateMethod : ApiCallMethod<UsergroupsCreateMethod, SuccessfulUsergroupsCreateResponse, ErrorUsergroupsCreateResponse, SlackUsergroupsCreateRequest>()