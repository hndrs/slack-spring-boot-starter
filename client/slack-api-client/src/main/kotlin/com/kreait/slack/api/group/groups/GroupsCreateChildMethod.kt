package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.createChild
 */
abstract class GroupsCreateChildMethod : ApiCallMethod<GroupsCreateChildMethod, SuccessfulGroupsCreateChildResponse, ErrorGroupsCreateChildResponse, GroupsCreateChildRequest>()

