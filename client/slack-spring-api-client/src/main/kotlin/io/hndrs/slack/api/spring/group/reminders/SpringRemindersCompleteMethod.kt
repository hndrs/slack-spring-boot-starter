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
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : RemindersCompleteMethod() {
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
