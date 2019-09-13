package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.group.users.ErrorListAllResponse
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserListAllMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultUserListAllMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UserListAllMethod() {

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultUserListAllMethod::class.java)
    }

    private val defaultUserListMethod: DefaultUserListMethod = DefaultUserListMethod(authToken, restTemplate)

    override fun request(): ApiCallResult<SuccessfulListAllResponse, ErrorListAllResponse> {

        val members = mutableListOf<Member>()
        var nextCursor: String? = null
        var error: String? = null

        do {

            val request = ListRequest(includeLocale = this.params.includeLocale, cursor = nextCursor)
            val result = defaultUserListMethod.with(request)
                    .onFailure { LOG.debug("Error while fetching all users, {}", it) }
                    .onSuccess { LOG.debug("Fetching users with {}\n{}", request, it) }
                    .invoke()

            if (result.wasSuccess()) {
                result.success?.members?.let { members.addAll(it) }
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
