package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsMarkRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsMarkResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.mark
 */
abstract class GroupsMarkMethod : ApiCallMethod<GroupsMarkMethod, SuccessfulGroupsMarkResponse, ErrorGroupsMarkResponse, GroupsMarkRequest>()
