package com.kreait.slack.broker.store.team

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.charset.Charset

/**
 * Default implementation of a local TeamStore
 * This implementation is meant for develop environments
 */
class FileTeamStore : TeamStore {


    companion object {

        private val LOG = LoggerFactory.getLogger(FileTeamStore::class.java)

        private const val fileName = "team-store.json"
        private val objectMapper = ObjectMapper()

        /**
         * Methods to set up the directory and the storage file
         */
        private fun homeDirectory(): String = System.getProperty("user.home")
                ?: throw IllegalArgumentException("Unable to load team-file:'user.home' System property is not set.")

        private fun dataFile(): File = File(homeDirectory(), ".slack/$fileName")


        private fun localTeamToTeam(team: LocalTeam): Team {
            return Team(
                    teamId = team.teamId,
                    teamName = team.teamName,
                    incomingWebhook = team.incomingWebhook?.let {
                        Team.IncomingWebhook(
                                channel = team.incomingWebhook.channel,
                                channelId = team.incomingWebhook.channelId,
                                configurationUrl = team.incomingWebhook.configurationUrl,
                                url = team.incomingWebhook.url
                        )
                    },
                    bot = Team.Bot(
                            userId = team.bot.userId,
                            accessToken = team.bot.accessToken
                    ))
        }

    }

    /**
     * File should be already present.
     * Creates the file in case it is missing
     */
    init {
        if (!dataFile().exists()) {
            LOG.info("Did not find team-file under ${dataFile().absolutePath}. Attempting to create it")
            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()
            objectMapper.writeValue(dataFile(), listOf<LocalTeam>())
            LOG.info("$fileName successfully created under ${dataFile().absolutePath}")
        } else if (dataFile().exists() && dataFile().isFile) {
            LOG.info("$fileName found")
            if (dataFile().length() == 0L) {
                LOG.info("File is empty, initializing with emtpty array")
                objectMapper.writeValue(dataFile(), listOf<LocalTeam>())
            }
        } else {
            LOG.error("Could not create file")
            throw IllegalStateException("$fileName seems to be a directory")
        }
    }


    /**
     * Operations on the local file
     */
    override fun findById(id: String): Team {
        val localTeams: List<LocalTeam> = objectMapper.readValue(dataFile())

        // Find the team with the given id in the list that was retrieved from the file
        val team = localTeams.find { it.teamId == id } ?: throw TeamNotFoundException("Team $id not found.")

        return localTeamToTeam(team)
    }

    override fun put(team: Team) {

        LOG.info("Inserting $team")
        val file = dataFile()

        val origin: List<LocalTeam> = objectMapper.readValue(file)

        // Build an Object out of the team that is given in via parameter
        val localTeam = LocalTeam(
                teamId = team.teamId,
                teamName = team.teamName,
                incomingWebhook = team.incomingWebhook?.let {
                    LocalTeam.IncomingWebhook(
                            channel = team.incomingWebhook.channel,
                            channelId = team.incomingWebhook.channelId,
                            configurationUrl = team.incomingWebhook.configurationUrl,
                            url = team.incomingWebhook.url)
                },
                bot = LocalTeam.Bot(
                        userId = team.bot.userId,
                        accessToken = team.bot.accessToken
                )
        )

        val result = origin.plus(localTeam)
        FileUtils.write(file, objectMapper.writeValueAsString(result), Charset.forName("UTF-8"))
    }

    override fun removeById(id: String) {

        val origin: List<LocalTeam> = objectMapper.readValue(dataFile())

        val teamToRemove = origin.find { it.teamId == id }

        teamToRemove?.let {
            objectMapper.writeValue(dataFile(), origin.minus(it))
        }

    }

    override fun findAll(): List<Team> {
        val localTeams: List<LocalTeam> = objectMapper.readValue(dataFile())
        return localTeams.map { localTeamToTeam(it) }
    }

    /**
     * Team that is saved to the file
     */
    @JacksonDataClass
    data class LocalTeam(@field: JsonProperty("team_id")
                         @param: JsonProperty("team_id")
                         val teamId: String,

                         @field: JsonProperty("team_name")
                         @param: JsonProperty("team_name")
                         val teamName: String,

                         @field: JsonProperty("incoming_webhook")
                         @param: JsonProperty("incoming_webhook")
                         val incomingWebhook: IncomingWebhook?,

                         @field: JsonProperty("bot")
                         @param: JsonProperty("bot")
                         val bot: Bot) {
        companion object {}

        /**
         * Bot-object that contains relevant bot-information
         */
        @JacksonDataClass
        data class Bot(
                @field: JsonProperty("user_id")
                @param: JsonProperty("user_id")
                val userId: String,

                @field: JsonProperty("access_token")
                @param: JsonProperty("access_token")
                val accessToken: String) {
            companion object
        }

        /**
         * Incoming-webhook object that can be used to post messages to a channel without user-interaction
         */
        @JacksonDataClass
        data class IncomingWebhook(
                @field:JsonProperty("channel")
                @param: JsonProperty("channel")
                val channel: String,

                @field: JsonProperty("channel_id")
                @param: JsonProperty("channel_id")
                val channelId: String,

                @field: JsonProperty("configuration_rl")
                @param: JsonProperty("configuration_rl")
                val configurationUrl: String,

                @field: JsonProperty("url")
                @param: JsonProperty("url")
                val url: String) {
            companion object
        }
    }
}
