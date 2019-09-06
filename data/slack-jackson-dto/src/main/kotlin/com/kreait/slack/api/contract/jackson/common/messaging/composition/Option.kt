package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A data class representing a single selectable item in a select menu or overflow menu,
 * known as Option object.
 *
 *
 * @property text A plain_text-only [Text] object defining the text shown in the option. (Max length 75 chars).
 * @property value A string value that will be passed to your app when this option object is selected (Max length 75 chars).
 * @property url A URL that will be loaded in the users browser when clicking that option.
 *
 * @see [Text]
 * @see [Slack API Documentation](https://api.slack.com/reference/messaging/composition-objects#option)
 */
data class Option(@JsonProperty("text") val text: Text,
                  @JsonProperty("value") val value: String,
                  @JsonProperty("url") val url: String? = "") {
    companion object
}

/**
 * A data class providing a way of grouping options in select menus
 *
 * @property text A plain_text-only [Text] object defining the label shown above the group of options. (Max length 75 chars).
 * @property options A list of option objects belonging to that options group. (Max 100 Items).
 * @see [Text]
 * @see [Option]
 * @see [Element]
 *
 * @see [Slack API Documentation](https://api.slack.com/reference/messaging/composition-objects#option-group)
 */
data class OptionGroup(@JsonProperty("label") val text: Text,
                       @JsonProperty("value") val options: List<Option>) {
    companion object
}
