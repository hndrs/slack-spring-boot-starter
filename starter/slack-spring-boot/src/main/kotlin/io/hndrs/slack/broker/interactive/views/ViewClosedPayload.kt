package io.hndrs.slack.broker.interactive.views

import com.slack.api.model.view.View

data class ViewClosedPayload(
    val enterprise: Enterprise? = null,
    val team: Team,
    val user: User,
    val apiAppId: String? = null,
    val token: String? = null,
    val view: View,
    val isEnterpriseInstall: Boolean = false,
    val isCleared: Boolean = false,
) {
    data class Enterprise(
        val id: String? = null,
        val name: String? = null,
    )

    data class Team(
        val id: String,
        val domain: String? = null,
        val enterpriseId: String? = null,
        val enterpriseName: String? = null,
    )

    data class User(
        val id: String,
        val username: String? = null,
        val name: String? = null,
        val teamId: String? = null,
    )

    companion object {
        const val TYPE = "view_closed"
    }
}
