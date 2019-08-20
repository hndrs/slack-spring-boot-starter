package com.kreait.slack.api.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class GroupsListMethod: ApiCallMethod<GroupsCreateMethod, SuccessfulGroupsCreateResponse, ErrorGroupsCreateResponse, GroupsCreateRequest>()