package io.hndrs.slack.broker.command

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.broker.util.JacksonDataClass

/**
 * A slash-command that was invoked
 *
 * @property token the token that can be used for further actions
 * @property teamId the team id
 * @property teamDomain the domain name of the team
 * @property channelId the channel id in which the slashcommand was invoked
 * @property channelName the channel id in which the slashcommand was invoked
 * @property userId the user id of the user that invoked the command
 * @property userName the name of the user that invoked the command
 * @property command the command that was invoked, starting with a "/"
 * @property text the text that was written after the command e.g. /test abc
 * @property responseUrl the response url that can be used to respond to the action
 * @property triggerId the trigger id which is used to open a dialog
 * @property enterpriseId the enterprise id which is only present, when the workspace is contained
 * in the enterprise grid
 * @property enterpriseName the enterprise name which is only present, when the workspace is contained
 * in the enterprise grid
 * @property apiAppId the slack apps Id. Can be used for handling commands from multiple applications or environments.
 */
@JacksonDataClass
data class SlashCommand constructor(
    @JsonProperty(TOKEN_PROPERTY_NAME) val token: String,
    @JsonProperty(TEAM_ID_PROPERTY_NAME) val teamId: String,
    @JsonProperty(TEAM_DOMAIN_PROPERTY_NAME) val teamDomain: String,
    @JsonProperty(CHANNEL_ID_PROPERTY_NAME) val channelId: String,
    @JsonProperty(CHANNEL_NAME_PROPERTY_NAME) val channelName: String,
    @JsonProperty(USER_ID_PROPERTY_NAME) val userId: String,
    @JsonProperty(USER_NAME_PROPERTY_NAME) val userName: String,
    @JsonProperty(COMMAND_PROPERTY_NAME) val command: String,
    @JsonProperty(TEXT_PROPERTY_NAME) val text: String,
    @JsonProperty(RESPONSE_URL_PROPERTY_NAME) val responseUrl: String,
    @JsonProperty(TRIGGER_ID_PROPERTY_NAME) val triggerId: String,
    @JsonProperty(ENTERPRISE_ID_PROPERTY_NAME) val enterpriseId: String?,
    @JsonProperty(ENTERPRISE_NAME_PROPERTY_NAME) val enterpriseName: String?,
    @JsonProperty(API_APP_ID_PROPERTY_NAME) val apiAppId: String,
) {
    companion object {
        const val TOKEN_PROPERTY_NAME = "token"
        const val TEAM_ID_PROPERTY_NAME = "team_id"
        const val TEAM_DOMAIN_PROPERTY_NAME = "team_domain"
        const val CHANNEL_ID_PROPERTY_NAME = "channel_id"
        const val CHANNEL_NAME_PROPERTY_NAME = "channel_name"
        const val USER_ID_PROPERTY_NAME = "user_id"
        const val USER_NAME_PROPERTY_NAME = "user_name"
        const val COMMAND_PROPERTY_NAME = "command"
        const val TEXT_PROPERTY_NAME = "text"
        const val RESPONSE_URL_PROPERTY_NAME = "response_url"
        const val TRIGGER_ID_PROPERTY_NAME = "trigger_id"
        const val ENTERPRISE_ID_PROPERTY_NAME = "enterprise_id"
        const val ENTERPRISE_NAME_PROPERTY_NAME = "enterprise_name"
        const val API_APP_ID_PROPERTY_NAME = "api_app_id"
    }
}
