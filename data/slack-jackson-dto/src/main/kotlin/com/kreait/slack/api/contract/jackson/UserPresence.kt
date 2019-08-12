package com.kreait.slack.api.contract.jackson

enum class UserPresence(val type: String) {
    AUTO("auto"),
    ACTIVE("active"),
    AWAY("away")
}
