package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsSetPurposeMethod : ApiCallMethod<GroupsSetPurposeMethod, SuccessfulGroupsSetPurposeResponse, ErrorGroupsSetPurposeResponse, GroupsSetPurposeRequest>()