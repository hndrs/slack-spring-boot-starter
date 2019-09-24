package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/groups.create
 */
abstract class GroupsCreateMethod : ApiCallMethod<GroupsCreateMethod, SuccessfulGroupsCreateResponse, ErrorGroupsCreateResponse, GroupsCreateRequest>()
