package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.LookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersLookupByEmailMethod : ApiCallMethod<UsersLookupByEmailMethod, SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse, LookupByEmailRequest>()
