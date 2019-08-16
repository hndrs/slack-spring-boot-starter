package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsCreateChildMethod : ApiCallMethod<GroupsCreateChildMethod, SuccessfulGroupsCreateChildResponse, ErrorGroupsCreateChildResponse, GroupsCreateChildRequest>()

