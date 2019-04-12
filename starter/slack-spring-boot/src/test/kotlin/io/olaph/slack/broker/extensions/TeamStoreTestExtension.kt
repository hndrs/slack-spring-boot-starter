package io.olaph.slack.broker.extensions

import io.olaph.slack.broker.store.Team

fun Team.Companion.sample(): Team = Team("TestTeamId", "TestTeamName", Team.IncomingWebhook.sample(), Team.Bot.sample())

fun Team.IncomingWebhook.Companion.sample(): Team.IncomingWebhook = Team.IncomingWebhook("TestChannel", "TestChannelId", "http://configuration-test.com", "http://test.com")

fun Team.Bot.Companion.sample(): Team.Bot = Team.Bot("UserId", "acccessToken")
