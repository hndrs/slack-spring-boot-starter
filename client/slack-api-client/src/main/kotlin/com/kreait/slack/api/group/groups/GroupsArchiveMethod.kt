package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.archive
 */
abstract class GroupsArchiveMethod : ApiCallMethod<GroupsArchiveMethod, SuccessfulGroupsArchiveResponse, ErrorGroupsArchiveResponse, GroupsArchiveRequest>()

