package io.hndrs.slack.api.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import io.hndrs.slack.api.contract.jackson.group.users.LookupByEmailRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 *
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersLookupByEmailMethod :
    ApiCallMethod<UsersLookupByEmailMethod, SuccessfulLookupByEmailResponse,
            ErrorLookupByEmailResponse, LookupByEmailRequest>()
