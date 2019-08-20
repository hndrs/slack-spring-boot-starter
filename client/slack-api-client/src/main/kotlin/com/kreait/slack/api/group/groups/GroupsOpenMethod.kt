package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsOpenRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsOpenResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsOpenMethod : ApiCallMethod<GroupsOpenMethod, SuccessfulGroupsOpenResponse, ErrorGroupsOpenResponse, GroupsOpenRequest>()