package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.setPurpose
 */
abstract class GroupsSetPurposeMethod : ApiCallMethod<GroupsSetPurposeMethod, SuccessfulGroupsSetPurposeResponse, ErrorGroupsSetPurposeResponse, GroupsSetPurposeRequest>()
