package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersInfoMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [RemindersMethodGroup.info]
 */
class SpringRemindersInfoMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.reminders.RemindersInfoMethod() {
    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersInfoResponse, ErrorRemindersInfoResponse> {

        val response = SlackRequestBuilder<RemindersInfoResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("reminders.info")
            .returnAsType(RemindersInfoResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersInfoResponse -> {
                val responseEntity = response.body as SuccessfulRemindersInfoResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersInfoResponse -> {
                val responseEntity = response.body as ErrorRemindersInfoResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
