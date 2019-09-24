package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.group.users.ErrorListAllResponse
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserListAllMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.listAll]
 */
@Suppress("UNCHECKED_CAST")
class SpringUserListAllMethod(authToken: String, restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UserListAllMethod() {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringUserListAllMethod::class.java)
    }

    private val springUserListMethod: SpringUserListMethod = SpringUserListMethod(authToken, restTemplate)

    override fun request(): ApiCallResult<SuccessfulListAllResponse, ErrorListAllResponse> {

        val members = mutableListOf<Member>()
        var nextCursor: String? = null
        var error: String? = null

        do {

            val request = ListRequest(includeLocale = this.params.includeLocale, cursor = nextCursor, limit = this.params.packageSize)
            val result = springUserListMethod.with(request)
                    .onFailure { LOG.debug("Error while fetching all users, {}", it) }
                    .onSuccess { LOG.debug("Fetching users with {}\n{}", request, it) }
                    .invoke()

            if (result.wasSuccess()) {
                result.success?.members?.let {
                    members.addAll(it)
                }
                nextCursor = result.success?.responseMetadata?.nextCursor
            } else {
                nextCursor = null
                error = result.failure?.error
            }
        } while (!nextCursor.isNullOrBlank() && error == null)

        return if (error != null) {
            val errorListAllResponse = ErrorListAllResponse(error)
            this.onFailure?.invoke(errorListAllResponse)
            ApiCallResult(failure = errorListAllResponse)
        } else {
            val successfulListAllResponse = SuccessfulListAllResponse(members)
            this.onSuccess?.invoke(successfulListAllResponse)
            ApiCallResult(success = successfulListAllResponse)
        }
    }
}
