package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

/**
 * A data-class representing either a user or a team identity.
 *
 * @property user Representing a user identity.
 * @property team Representing a team identity.
 *
 * @see [User]
 * @see [Team]
 */
@JacksonDataClass
data class Identity(
        @JsonProperty("user") val user: User,
        @JsonProperty("team") val team: Team
) {

    /**
     * A data-class used for user identities.
     *
     * @property name The name of the user.
     * @property id Indicates a unique id for the user.
     * @property email The E-Mail that is associated with the user.
     * @property image24 The user avatar with the dimensions of 24x24px.
     * @property image32 The user avatar with the dimensions of 32x32px.
     * @property image48 The user avatar with the dimensions of 48x48px.
     * @property image72 The user avatar with the dimensions of 72x72px.
     * @property image192 The user avatar with the dimensions of 192x192px.
     */
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

    companion object
}
