package io.hndrs.slack.broker.receiver

import io.hndrs.slack.broker.store.team.Team

/**
 * Receiver where installations will be forwarded to
 */
interface InstallationReceiver {

    /**
     * retrieves the installation-request
     *
     * @param code the code with which you can obtain the oauth token
     * @param state a state with which you can pass additional data
     * @param team the team that requests the installation
     */
    fun onReceiveInstallation(code: String, state: String, team: Team)
}
