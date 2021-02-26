package io.hndrs.slack.api.spring.group.users

import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.group.users.ErrorListAllResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UserListAllMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.listAll]
 */
@Suppress("UNCHECKED_CAST")
class SpringUserListAllMethod(authToken: String, restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()) :
    io.hndrs.slack.api.group.users.UserListAllMethod() {

    private val springUserListMethod: SpringUserListMethod = SpringUserListMethod(authToken, restTemplate)

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulListAllResponse, ErrorListAllResponse> {

        val members = mutableListOf<Member>()
        var nextCursor: String? = null
        var error: String? = null

        do {

            val request = ListRequest(
                includeLocale = this.params.includeLocale,
                cursor = nextCursor,
                limit = this.params.packageSize
            )
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
            io.hndrs.slack.api.group.ApiCallResult(failure = errorListAllResponse)
        } else {
            val successfulListAllResponse = SuccessfulListAllResponse(members)
            this.onSuccess?.invoke(successfulListAllResponse)
            io.hndrs.slack.api.group.ApiCallResult(success = successfulListAllResponse)
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SpringUserListAllMethod::class.java)
    }
}
