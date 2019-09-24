package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRepliesResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.replies
 */
abstract class GroupsRepliesMethod : ApiCallMethod<GroupsRepliesMethod, SuccessfulGroupsRepliesResponse, ErrorGroupsRepliesResponse, GroupsRepliesRequest>()
