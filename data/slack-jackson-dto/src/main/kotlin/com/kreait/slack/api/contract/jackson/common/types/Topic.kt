package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

/**
 * The topic of a Conversation, Group or Channel
 *
 * @property value the text of the topic
 * @property createdBy the author of the topic
 * @property lastModifiedAt the date when the topic was last modified
 */
@JacksonDataClass
data class Topic(
    @JsonProperty("value") val value: String,
    @JsonProperty("creator") val createdBy: String,
    @InstantToInt @JsonProperty("last_set") val lastModifiedAt: Instant
) {
    companion object
}
