package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.group.groups.GroupsLeaveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultGroupsLeaveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsLeaveMethod()