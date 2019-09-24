package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRenameRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRenameResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.rename
 */
abstract class GroupsRenameMethod : ApiCallMethod<GroupsRenameMethod, SuccessfulGroupsRenameResponse, ErrorGroupsRenameResponse, GroupsRenameRequest>()
