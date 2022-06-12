package io.hndrs.slack.broker.extensions

import io.hndrs.slack.broker.store.team.Team

fun Team.Companion.sample(): Team = Team("TestTeamId", "TestTeamName", "accessToken")
