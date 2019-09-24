package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsKickRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsKickResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.kick
 */
abstract class GroupsKickMethod : ApiCallMethod<GroupsKickMethod, SuccessfulGroupsKickResponse, ErrorGroupsKickResponse, GroupsKickRequest>()
