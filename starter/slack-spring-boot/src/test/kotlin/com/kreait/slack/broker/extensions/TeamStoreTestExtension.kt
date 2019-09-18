package com.kreait.slack.broker.extensions

import com.kreait.slack.broker.store.team.Team

fun Team.Companion.sample(): Team = Team("TestTeamId", "TestTeamName", Team.IncomingWebhook.sample(), Team.Bot.sample())

fun Team.IncomingWebhook.Companion.sample(): Team.IncomingWebhook = Team.IncomingWebhook("TestChannel", "TestChannelId", "http://configuration-test.com", "http://test.com")

fun Team.Bot.Companion.sample(): Team.Bot = Team.Bot("UserId", "acccessToken")
