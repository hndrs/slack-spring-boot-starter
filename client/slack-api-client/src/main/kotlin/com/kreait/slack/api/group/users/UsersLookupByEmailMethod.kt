package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersLookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersLookupByEmailMethod : ApiCallMethod<UsersLookupByEmailMethod, SuccessfulUsersLookupByEmailResponse, ErrorUsersLookupByEmailResponse, SlackUsersLookupByEmailRequest>()
