package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersCompleteMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

class DefaultRemindersCompleteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersCompleteMethod() {
    override fun request(): ApiCallResult<SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse> {

        val response = SlackRequestBuilder<RemindersCompleteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("reminders.complete")
                .returnAsType(RemindersCompleteResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersCompleteResponse -> {
                val responseEntity = response.body as SuccessfulRemindersCompleteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersCompleteResponse -> {
                val responseEntity = response.body as ErrorRemindersCompleteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
