package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersListMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [RemindersMethodGroup.list]
 */
class SpringRemindersListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.reminders.RemindersListMethod() {
    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersListResponse, ErrorRemindersListResponse> {

        val response = SlackRequestBuilder<RemindersListResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("reminders.list")
            .returnAsType(RemindersListResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersListResponse -> {
                val responseEntity = response.body as SuccessfulRemindersListResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersListResponse -> {
                val responseEntity = response.body as ErrorRemindersListResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
