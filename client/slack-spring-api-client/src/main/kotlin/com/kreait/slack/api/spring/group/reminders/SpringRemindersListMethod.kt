package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersListMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [RemindersMethodGroup.list]
 */
class SpringRemindersListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersListMethod() {
    override fun request(): ApiCallResult<SuccessfulRemindersListResponse, ErrorRemindersListResponse> {

        val response = SlackRequestBuilder<RemindersListResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("reminders.list")
                .returnAsType(RemindersListResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersListResponse -> {
                val responseEntity = response.body as SuccessfulRemindersListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersListResponse -> {
                val responseEntity = response.body as ErrorRemindersListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
