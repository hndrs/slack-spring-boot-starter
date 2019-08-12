package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * https://api.slack.com/reference/messaging/composition-objects#confirm
 */
data class Confirmation(@JsonProperty("title") val title: Text,
                        @JsonProperty("text") val text: Text,
                        @JsonProperty("confirm") val confirm: Text,
                        @JsonProperty("deny") val deny: Text) {
    companion object

}
