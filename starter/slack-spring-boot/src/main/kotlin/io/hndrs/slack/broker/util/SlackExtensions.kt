package io.hndrs.slack.broker.util

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import io.hndrs.slack.broker.store.team.Team

fun Slack.methods(team: Team): MethodsClient = this.methods(team.accessToken, team.teamId)
