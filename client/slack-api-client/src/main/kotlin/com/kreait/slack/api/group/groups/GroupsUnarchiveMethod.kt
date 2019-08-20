package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsUnarchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsUnarchiveMethod : ApiCallMethod<GroupsUnarchiveMethod, SuccessfulGroupsUnarchiveResponse, ErrorGroupsUnarchiveResponse, GroupsUnarchiveRequest>()

