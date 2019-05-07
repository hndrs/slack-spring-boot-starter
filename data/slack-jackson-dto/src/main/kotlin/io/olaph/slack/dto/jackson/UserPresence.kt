package io.olaph.slack.dto.jackson

enum class UserPresence(val type: String) {
    AUTO("auto"),
    AWAY("away")
}