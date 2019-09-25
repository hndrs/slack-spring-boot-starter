package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsListRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsListResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.listGroups
 */
abstract class GroupsListMethod : ApiCallMethod<GroupsListMethod, SuccessfulGroupsListResponse, ErrorGroupsListResponse, GroupsListRequest>()
