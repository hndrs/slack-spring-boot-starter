package io.olaph.slack.broker.receiver

import io.olaph.slack.broker.store.Team

interface InstallationReceiver {

    fun onReceiveInstallation(code: String, state: String, team: Team)
}
