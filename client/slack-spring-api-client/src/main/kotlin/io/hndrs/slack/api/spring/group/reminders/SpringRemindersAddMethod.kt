package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersAddMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
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
