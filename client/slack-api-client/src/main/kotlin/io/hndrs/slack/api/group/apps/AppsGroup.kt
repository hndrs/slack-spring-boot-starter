package io.hndrs.slack.api.group.apps

interface AppsGroup {

    /**
     * Create an app from an app manifest.
     * https://api.slack.com/methods/apps.manifest.create
     */
    fun manifestCreate(appConfigurationToken: String)

    /**
     * Permanently deletes an app created through app manifests
     * https://api.slack.com/methods/apps.manifest.delete
     */
    fun manifestDelete(appConfigurationToken: String)

    /**
     * Update an app from an app manifest
     * https://api.slack.com/methods/apps.manifest.delete
     */
    fun manifestUpdate(appConfigurationToken: String)

    /**
     * Validate an app manifest
     * https://api.slack.com/methods/apps.manifest.delete
     */
    fun manifestValidate(appConfigurationToken: String)
}
