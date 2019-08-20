package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsLeaveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsLeaveMethod : ApiCallMethod<GroupsLeaveMethod, SuccessfulGroupsLeaveResponse, ErrorGroupsLeaveResponse, GroupsLeaveRequest>()