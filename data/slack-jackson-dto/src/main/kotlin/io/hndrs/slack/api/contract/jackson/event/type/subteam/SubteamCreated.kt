package io.hndrs.slack.api.contract.jackson.event.type.subteam

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.event.Event
import io.hndrs.slack.api.contract.jackson.group.usergroups.UserGroup
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class SubteamCreated(
    override val type: String,
    @JsonProperty("subteam") val userGroup: UserGroup
) : Event(type) {

    override fun slackTypeString() = TYPE

    companion object {
        /**
         * A User Group has been added to the workspace
         * https://api.slack.com/events/subteam_created
         */
        const val TYPE = "subteam_created"
    }
}
