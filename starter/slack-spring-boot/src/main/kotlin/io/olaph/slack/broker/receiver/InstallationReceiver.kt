package io.olaph.slack.broker.receiver

interface InstallationReceiver {

    fun onReceiveInstallation(code: String, state: String)
}
