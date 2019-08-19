package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsHistoryResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsHistoryMethod : ApiCallMethod<GroupsHistoryMethod, SuccessfulGroupsHistoryResponse, ErrorGroupsHistoryResponse, GroupsHistoryRequest>()
