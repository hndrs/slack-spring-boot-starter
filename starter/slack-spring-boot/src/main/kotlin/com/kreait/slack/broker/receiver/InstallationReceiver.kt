package com.kreait.slack.broker.receiver

import com.kreait.slack.broker.store.Team

interface InstallationReceiver {

    fun onReceiveInstallation(code: String, state: String, team: Team)
}
