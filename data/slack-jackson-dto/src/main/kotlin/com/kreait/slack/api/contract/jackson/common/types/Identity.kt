package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class Identity(
        @JsonProperty("user") val user: User,
        @JsonProperty("team") val team: Team
) {

    data class User(val name: String,
                    val id: String,
                    val email: String?,
                    val image24: String?,
                    val image32: String?,
                    val image48: String?,
                    val image72: String?,
                    val image192: String?) {
        companion object
    }

    data class Team(val id: String,
                    val name: String?) {
        companion object
    }

    companion object
}
