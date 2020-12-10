package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.reminders.RemindersAddMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [RemindersMethodGroup.add]
 */
class SpringRemindersAddMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : RemindersAddMethod() {
    override fun request(): ApiCallResult<SuccessfulRemindersAddResponse, ErrorRemindersAddResponse> {

        val response = SlackRequestBuilder<RemindersAddResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("reminders.add")
            .returnAsType(RemindersAddResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersAddResponse -> {
                val responseEntity = response.body as SuccessfulRemindersAddResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersAddResponse -> {
                val responseEntity = response.body as ErrorRemindersAddResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
