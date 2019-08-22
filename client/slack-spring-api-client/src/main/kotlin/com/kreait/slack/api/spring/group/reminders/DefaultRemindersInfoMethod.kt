package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.group.reminders.RemindersInfoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultRemindersInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersInfoMethod()