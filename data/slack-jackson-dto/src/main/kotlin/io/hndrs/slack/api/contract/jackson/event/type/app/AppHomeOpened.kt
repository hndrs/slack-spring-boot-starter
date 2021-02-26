package io.hndrs.slack.api.contract.jackson.event.type.app

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.common.messaging.Block
import io.hndrs.slack.api.contract.jackson.event.Event
import io.hndrs.slack.api.contract.jackson.util.InstantToString
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class AppHomeOpened(
    override val type: String,
    @JsonProperty("channel")
    val channel: String?,
    @InstantToString @JsonProperty("event_ts")
    val eventTimestamp: Instant?,
    @JsonProperty("tab")
    val tab: String?,
    @JsonProperty("user")
    val user: String?,
    @JsonProperty("view")
    val view: View?
) : Event(type) {

    override fun slackTypeString(): String = TYPE

    companion object {
        /**
         * A new member has joined
         * https://api.slack.com/events/app_home_opened
         */
        const val TYPE = "app_home_opened"
    }

}

data class View(
    @JsonProperty("app_id")
    val appId: String?,
    @JsonProperty("app_installed_team_id")
    val appInstalledTeamId: String?,
    @JsonProperty("blocks")
    val blocks: List<Block>?,
    @JsonProperty("bot_id")
    val botId: String?,
    @JsonProperty("callback_id")
    val callbackId: String?,
    @JsonProperty("clear_on_close")
    val clearOnClose: Boolean?,
    @JsonProperty("external_id")
    val externalId: String?,
    @JsonProperty("hash")
    val hash: String?,
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("notify_on_close")
    val notifyOnClose: Boolean?,
    @JsonProperty("private_metadata")
    val privateMetadata: String?,
    @JsonProperty("root_view_id")
    val rootViewId: String?,
    @JsonProperty("state")
    val state: Any?,
    @JsonProperty("team_id")
    val teamId: String?,
    @JsonProperty("type")
    val type: String?
)
