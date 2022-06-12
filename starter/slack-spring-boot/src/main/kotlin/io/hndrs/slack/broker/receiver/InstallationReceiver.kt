package io.hndrs.slack.broker.receiver

import com.slack.api.methods.MethodsClient
import io.hndrs.slack.broker.store.team.Team

/**
 * Receiver where installations will be forwarded to
 */
interface InstallationReceiver {

    /**
     * retrieves the installation-request
     *
     * @param methods already authorized methods client
     * @param team the team that requests the installation
     */
    fun onInstallation(team: Team, methods: MethodsClient)
}
