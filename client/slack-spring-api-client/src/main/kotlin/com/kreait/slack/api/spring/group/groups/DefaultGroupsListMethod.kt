package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.group.groups.GroupsListMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultGroupsListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsListMethod()