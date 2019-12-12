package com.kreait.slack.broker.data.team

import com.kreait.slack.broker.data.DataController
import com.kreait.slack.broker.store.team.Team
import com.kreait.slack.broker.store.team.TeamStore
import org.springframework.web.bind.annotation.GetMapping

@DataController
class TeamController(private val teamStore: TeamStore) {

    @GetMapping(path = ["/teams"])
    fun teams(): List<Team> {
        return teamStore.findAll();
    }
}
