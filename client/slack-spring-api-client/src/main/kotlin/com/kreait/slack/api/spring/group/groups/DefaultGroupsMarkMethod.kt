package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.group.groups.GroupsMarkMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultGroupsMarkMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsMarkMethod()