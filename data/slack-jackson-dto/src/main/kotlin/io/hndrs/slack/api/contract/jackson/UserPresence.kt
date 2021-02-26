package io.hndrs.slack.api.contract.jackson


/**
 * The Presence status of a user
 * can be [UserPresence.AUTO],[UserPresence.ACTIVE] or [UserPresence.AWAY]
 */
enum class UserPresence(val type: String) {
    AUTO("auto"),
    ACTIVE("active"),
    AWAY("away")
}
