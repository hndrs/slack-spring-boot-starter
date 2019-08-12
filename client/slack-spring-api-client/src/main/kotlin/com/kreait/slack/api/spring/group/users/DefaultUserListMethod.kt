package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.users.ListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultUserListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.formUrlTemplate()) : UserListMethod() {
    private var result: SuccessfulListResponse? = null
    var nextCursor = ""

    override fun request(): ApiCallResult<SuccessfulListResponse, ErrorListResponse> {
        val map = this.params.copy(cursor = nextCursor).toRequestMap()
        val response = postUsersListRequest(map)
        when (response.body!!) {
            is SuccessfulListResponse -> {
                val responseEntity = response.body as SuccessfulListResponse
                if (result == null) {
                    result = responseEntity
                }
                nextCursor = responseEntity.responseMetadata.nextCursor!!

                return if (nextCursor != "") {
                    result = result!!.copy(members = result!!.members.union(responseEntity.members).toList())
                    request()
                } else {
                    if (result == null) {
                        this.onSuccess?.invoke(responseEntity)
                        ApiCallResult(success = responseEntity)
                    } else {
                        result = result!!.copy(members = result!!.members.union(responseEntity.members).toList())
                        this.onSuccess?.invoke(result!!)
                        ApiCallResult(success = result)
                    }
                }
            }
            is ErrorListResponse -> {
                val responseEntity = response.body as ErrorListResponse
                this.onFailure?.invoke(responseEntity)
                return ApiCallResult(failure = responseEntity)
            }
        }
    }

    private fun postUsersListRequest(params: Map<String, String>): ResponseEntity<ListResponse> {
        return SlackRequestBuilder<ListResponse>(authToken, restTemplate)
                .toMethod("users.list")
                .returnAsType(ListResponse::class.java)
                .postUrlEncoded(params)
    }
}
