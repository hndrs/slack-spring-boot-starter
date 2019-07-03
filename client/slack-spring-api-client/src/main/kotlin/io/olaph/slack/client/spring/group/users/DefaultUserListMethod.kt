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
class DefaultUserListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UserListMethod() {
    private var result: SuccessfulUserListResponse? = null
    var nextCursor = ""
    override fun request(): ApiCallResult<SuccessfulUserListResponse, ErrorUserListResponse> {
        println("cursor before request: $nextCursor")
        val map = this.params.copy(cursor = nextCursor).toRequestMap()
        println(map)
        val response = postUsersListRequest(map)
        when (response.body!!) {
            is SuccessfulUserListResponse -> {
                val responseEntity = response.body as SuccessfulUserListResponse
                if (result == null) {
                    println("first iteration")
                    result = responseEntity
                }
                nextCursor = responseEntity.responseMetadata.nextCursor!!
                println("next_cursor: $nextCursor")
                if (nextCursor != "") {
                    result!!.members.toMutableList().addAll(responseEntity.members)
                    println("added members")
                    request()
                } else {
                    return if (result == null)
                        ApiCallResult(success = responseEntity)
                    else
                        ApiCallResult(success = result)
                }
            }
            is ErrorUserListResponse -> {
                val responseEntity = response.body as ErrorUserListResponse
                this.onFailure?.invoke(responseEntity)
                return ApiCallResult(failure = responseEntity)
            }
        }
        return ApiCallResult(failure = ErrorUserListResponse(false, "error"))
    }

    private fun postUsersListRequest(params: Map<String, String>): ResponseEntity<UserListResponse> {
        println(params)
        return SlackRequestBuilder<UserListResponse>(authToken, restTemplate)
                .toMethod("users.list")
                .returnAsType(UserListResponse::class.java)
                .postUrlEncoded(params)
    }
}
