package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UserListMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse
import io.olaph.slack.dto.jackson.group.users.UserListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultUserListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.formUrlTemplate()) : UserListMethod() {
    private var result: SuccessfulUserListResponse? = null
    var nextCursor = ""

    override fun request(): ApiCallResult<SuccessfulUserListResponse, ErrorUserListResponse> {
        val map = this.params.copy(cursor = nextCursor).toRequestMap()
        val response = postUsersListRequest(map)
        when (response.body!!) {
            is SuccessfulUserListResponse -> {
                val responseEntity = response.body as SuccessfulUserListResponse
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
            is ErrorUserListResponse -> {
                val responseEntity = response.body as ErrorUserListResponse
                this.onFailure?.invoke(responseEntity)
                return ApiCallResult(failure = responseEntity)
            }
        }
    }

    private fun postUsersListRequest(params: Map<String, String>): ResponseEntity<UserListResponse> {
        return SlackRequestBuilder<UserListResponse>(authToken, restTemplate)
                .toMethod("users.list")
                .returnAsType(UserListResponse::class.java)
                .postUrlEncoded(params)
    }
}