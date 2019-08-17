package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInviteRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInviteResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsInviteMethod : ApiCallMethod<GroupsInviteMethod, SuccessfulGroupsInviteResponse, ErrorGroupsInviteResponse, GroupsInviteRequest>()
