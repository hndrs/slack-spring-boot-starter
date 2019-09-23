package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersInfoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class SpringRemindersInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersInfoMethod() {
    override fun request(): ApiCallResult<SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse> {

        val response = SlackRequestBuilder<RemindersInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("reminders.info")
                .returnAsType(RemindersInfoResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersInfoResponse -> {
                val responseEntity = response.body as SuccessfulRemindersInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersInfoResponse -> {
                val responseEntity = response.body as ErrorRemindersInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
