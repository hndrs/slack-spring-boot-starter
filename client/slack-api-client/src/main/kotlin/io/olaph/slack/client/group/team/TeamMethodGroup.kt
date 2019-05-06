package io.olaph.slack.client.group.team

interface TeamMethodGroup {

    /**
     * 	Retrieve a team's profile.
     */
    fun getProfile(authToken: String): TeamGetProfileMethod
}