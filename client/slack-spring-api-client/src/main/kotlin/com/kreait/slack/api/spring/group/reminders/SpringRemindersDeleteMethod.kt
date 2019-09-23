package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersDeleteMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringRemindersDeleteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersDeleteMethod() {
    override fun request(): ApiCallResult<SuccessfulRemindersDeleteResponse, ErrorRemindersDeleteResponse> {

        val response = SlackRequestBuilder<RemindersDeleteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("reminders.delete")
                .returnAsType(RemindersDeleteResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersDeleteResponse -> {
                val responseEntity = response.body as SuccessfulRemindersDeleteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersDeleteResponse -> {
                val responseEntity = response.body as ErrorRemindersDeleteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
