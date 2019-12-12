package com.kreait.slack.broker.store.team

/**
 * In Memory TeamStore
 * This Default implementation should not be used on production environments
 */
class InMemoryTeamStore(private val teams: MutableMap<String, Team> = mutableMapOf()) : TeamStore {

    override fun findById(id: String): Team {
        return teams[id] ?: throw TeamNotFoundException("team with id $id not found")
    }

    override fun put(team: Team) {
        teams[team.teamId] = team
    }

    override fun removeById(id: String) {
        teams.remove(id)
    }

    override fun findAll(): List<Team> {
        return teams.entries.map { it.value }
    }
}
