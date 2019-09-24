package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.history
 */
abstract class GroupsHistoryMethod : ApiCallMethod<GroupsHistoryMethod, SuccessfulGroupsHistoryResponse, ErrorGroupsHistoryResponse, GroupsHistoryRequest>()
