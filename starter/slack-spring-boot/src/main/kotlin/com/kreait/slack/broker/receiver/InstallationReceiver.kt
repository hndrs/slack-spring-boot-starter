package com.kreait.slack.broker.receiver

import com.kreait.slack.broker.store.team.Team

interface InstallationReceiver {

    fun onReceiveInstallation(code: String, state: String, team: Team)
}
