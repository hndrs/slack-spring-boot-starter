package com.kreait.slack.api.contract.jackson.common.types

/**
 * A data-class used for team identities.
 *
 * @property id Indicates a unique id for the team.
 * @property name The name of the team.
 */
data class Team(
    val id: String,
    val name: String?
) {
    companion object
}
