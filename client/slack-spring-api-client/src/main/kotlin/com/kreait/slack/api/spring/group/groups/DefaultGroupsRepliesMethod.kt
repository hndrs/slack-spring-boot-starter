package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.group.groups.GroupsRepliesMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.springframework.web.client.RestTemplate

class DefaultGroupsRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : GroupsRepliesMethod()