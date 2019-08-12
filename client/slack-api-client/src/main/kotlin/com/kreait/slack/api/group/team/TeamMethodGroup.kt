package com.kreait.slack.api.group.team

interface TeamMethodGroup {

    /**
     * 	Retrieve a team's profile.
     */
    fun getProfile(authToken: String): TeamGetProfileMethod
}
