package io.olaph.slack.broker.store

/**
 * Used to handle App-installations
 */
interface TeamStore {

    /**
     * returns a Team by its Id
     */
    fun findById(id: String): Team

    /**
     * Adds a Team to the Database
     */
    fun put(team: Team)

    /**
     * removes a Team by its Id
     */
    fun removeById(id: String)
}

class TeamNotFoundException(override val message: String?) : RuntimeException(message)

/**
 * describes a Team that installed your App
 */
data class Team(val teamId: String,
                val teamName: String,
                val incomingWebhook: IncomingWebhook,
                val bot: Bot) {
    companion object {}

    data class Bot(
            val userId: String,
            val accessToken: String) {
        companion object
    }

    data class IncomingWebhook(
            val channel: String,
            val channelId: String,
            val configurationUrl: String,
            val url: String) {
        companion object
    }
}

