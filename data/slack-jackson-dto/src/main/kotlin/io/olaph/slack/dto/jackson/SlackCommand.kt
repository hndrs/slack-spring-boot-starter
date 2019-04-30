package io.olaph.slack.dto.jackson

import com.fasterxml.jackson.annotation.JsonProperty

@JacksonDataClass
data class SlackCommand constructor(@JsonProperty(TOKEN_PROPERTY_NAME) val token: String,
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
                                    @JsonProperty(ENTERPRISE_NAME_PROPERTY_NAME) val enterpriseName: String?) {
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
    }
}
