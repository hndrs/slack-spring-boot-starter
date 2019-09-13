package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class Message(
        @JsonProperty("type") val type: String? = null,
        @JsonProperty("subtype") val subtype: String? = null,
        @JsonProperty("text") val text: String? = null,
        @InstantToString @JsonProperty("ts") val timestamp: Instant,
        @JsonProperty("username") val username: String? = null,
        @JsonProperty("attachments") val attachments: List<Attachment>? = listOf(),
        @JsonProperty("blocks") val blocks: List<Block>? = listOf(),
        @JsonProperty("bot_id") val botId: String? = null) {
    companion object
}
