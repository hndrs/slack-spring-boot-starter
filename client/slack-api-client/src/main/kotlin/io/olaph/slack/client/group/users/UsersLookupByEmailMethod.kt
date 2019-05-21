package io.olaph.slack.client.group.users

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersLookupByEmailRequest

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
abstract class UsersLookupByEmailMethod : ApiCallMethod<UsersLookupByEmailMethod, SuccessfulUsersLookupByEmailResponse, ErrorUsersLookupByEmailResponse, SlackUsersLookupByEmailRequest>()