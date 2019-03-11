package io.olaph.slack.dto.jackson

import com.fasterxml.jackson.annotation.JsonProperty

@JacksonDataClass
data class SlackCommand constructor(@JsonProperty("token") val token: String,
                                    @JsonProperty("team_id") val teamId: String,
                                    @JsonProperty("team_domain") val teamDomain: String,
                                    @JsonProperty("channel_id") val channelId: String,
                                    @JsonProperty("channel_name") val channelName: String,
                                    @JsonProperty("user_id") val userId: String,
                                    @JsonProperty("user_name") val userName: String,
                                    @JsonProperty("command") val command: String,
                                    @JsonProperty("text") val text: String,
                                    @JsonProperty("response_url") val responseUrl: String,
                                    @JsonProperty("trigger_id") val triggerId: String,
                                    @JsonProperty("enterprise_id") val enterprise_id: String?,
                                    @JsonProperty("enterprise_name") val enterprise_name: String?) {
    companion object
}
