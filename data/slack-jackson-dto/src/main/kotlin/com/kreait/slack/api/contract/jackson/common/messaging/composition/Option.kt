package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * https://api.slack.com/reference/messaging/composition-objects#option
 */
data class Option(@JsonProperty("text") val text: Text,
                  @JsonProperty("value") val value: String) {
    companion object

}

/**
 * https://api.slack.com/reference/messaging/composition-objects#option-group
 */
data class OptionGroup(@JsonProperty("label") val text: Text,
                       @JsonProperty("value") val options: List<Option>) {
    companion object

}
