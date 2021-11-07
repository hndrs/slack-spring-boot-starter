package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.reminders.RemindersDeleteMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [RemindersMethodGroup.delete]
 */
class SpringRemindersDeleteMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : RemindersDeleteMethod() {
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
