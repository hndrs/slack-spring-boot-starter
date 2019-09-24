package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInfoRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInfoResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.info
 */
abstract class GroupsInfoMethod : ApiCallMethod<GroupsInfoMethod, SuccessfulGroupsInfoResponse, ErrorGroupsInfoResponse, GroupsInfoRequest>()
