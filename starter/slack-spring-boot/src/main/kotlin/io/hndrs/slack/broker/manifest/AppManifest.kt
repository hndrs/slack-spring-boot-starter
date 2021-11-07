package io.hndrs.slack.broker.manifest

import com.fasterxml.jackson.annotation.JsonProperty

data class AppManifest(

    @JsonProperty("_metadata")
    val _metadata: Metadata,
    val display_information: DisplayInformation,
    val features: Features,
    val oauth_config: OauthConfig,
    val settings: Settings
)

data class Metadata(
    val major_version: Int,
    val minor_version: Int
)

data class DisplayInformation(
    val background_color: String,
    val description: String,
    val long_description: String,
    val name: String
)

data class Features(
    val app_home: AppHome,
    val bot_user: BotUser,
    val slash_commands: List<SlashCommand>
)

data class OauthConfig(
    val redirect_urls: List<String>,
    val scopes: Scopes
)

data class Settings(
    val interactivity: Interactivity,
    val socket_mode_enabled: Boolean
)

data class AppHome(
    val home_tab_enabled: Boolean,
    val messages_tab_enabled: Boolean,
    val messages_tab_read_only_enabled: Boolean
)

data class BotUser(
    val display_name: String
)

data class SlashCommand(
    val command: String,
    val description: String,
    val url: String,
    val usage_hint: String
)

data class Scopes(
    val bot: List<String>
)

data class Interactivity(
    val is_enabled: Boolean,
    val request_url: String
)
