package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.group.reminders.RemindersAddMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultRemindersAddMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : RemindersAddMethod()