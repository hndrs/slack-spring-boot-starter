package com.kreait.slack.broker.store.team

/**
 * Used to handle App-installations
 */
interface TeamStore {

    /**
     * returns a [Team] by its Id
     * @param id the teamId you want to find a [Team] for
     */
    fun findById(id: String): Team

    /**
     * Adds a [Team] to the Database
     * @param team the [Team] you want to add
     */
    fun put(team: Team)

    /**
     * removes a [Team] by its Id
     * @id the id of the [Team] you want to remove
     */
    fun removeById(id: String)

    /**
     * Returns all teams
     *
     */
    fun findAll(): List<Team>
}

/**
 * Exception that is thrown when a requested team is not existing
 *
 * @property message the detailed error message
 */
class TeamNotFoundException(override val message: String?) : RuntimeException(message)

/**
 * describes a Team that installed your App
 */
data class Team(val teamId: String,
                val teamName: String,
                val incomingWebhook: IncomingWebhook?,
                val bot: Bot) {
    companion object {}

    /**
     * contains relevant information about the installed bot
     */
    data class Bot(
            val userId: String,
            val accessToken: String) {
        companion object
    }

    /**
     * Incoming-webhook object that can be used to post messages to a channel without user-interaction
     */
    data class IncomingWebhook(
            val channel: String,
            val channelId: String,
            val configurationUrl: String,
            val url: String) {
        companion object
    }
}

