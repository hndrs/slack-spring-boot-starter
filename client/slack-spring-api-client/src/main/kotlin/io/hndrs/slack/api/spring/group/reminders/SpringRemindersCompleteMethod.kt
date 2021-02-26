package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersCompleteMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [RemindersMethodGroup.complete]
 */
class SpringRemindersCompleteMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.reminders.RemindersCompleteMethod() {
    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulRemindersCompleteResponse, ErrorRemindersCompleteResponse> {

        val response = SlackRequestBuilder<RemindersCompleteResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("reminders.complete")
            .returnAsType(RemindersCompleteResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulRemindersCompleteResponse -> {
                val responseEntity = response.body as SuccessfulRemindersCompleteResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorRemindersCompleteResponse -> {
                val responseEntity = response.body as ErrorRemindersCompleteResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
