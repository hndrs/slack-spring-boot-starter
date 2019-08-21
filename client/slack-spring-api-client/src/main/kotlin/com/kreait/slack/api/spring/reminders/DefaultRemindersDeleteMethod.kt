package com.kreait.slack.api.spring.reminders

import com.kreait.slack.api.group.reminders.RemindersDeleteMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultRemindersDeleteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersDeleteMethod()