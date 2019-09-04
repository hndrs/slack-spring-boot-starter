package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A data class representing a confirmation dialog object.
 *
 * This object provides a confirmation step to any interactive element in your app.
 * The possible actions are either a confirmation or a deny.
 *
 *
 * @property title A plain_text-only [Text] object defining the title. (Max length 100 chars).
 * @property text A [Text] object for explanatory text that appears in the confirmation dialog. (Max length 300 chars).
 * @property confirm A plain_text-only [Text] object that defines the label of the confirmation button. (Max length 30 chars).
 * @property deny A plain_text-only [Text] object that defines the label of the deny button. (Max length 30 chars).
 *
 * @see [Text]
 * @see [Slack API Documentation](https://api.slack.com/reference/messaging/composition-objects#confirm)
 */
data class Confirmation(@JsonProperty("title") val title: Text,
                        @JsonProperty("text") val text: Text,
                        @JsonProperty("confirm") val confirm: Text,
                        @JsonProperty("deny") val deny: Text) {
    companion object
}
