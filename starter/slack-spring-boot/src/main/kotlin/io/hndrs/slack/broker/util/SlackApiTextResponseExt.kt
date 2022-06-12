package io.hndrs.slack.broker.util

import com.slack.api.methods.SlackApiTextResponse

fun <T : SlackApiTextResponse, R> T.on(onSuccess: (response: T) -> R, onFailure: (response: T) -> R): R {
    return if (this.isOk) {
        onSuccess(this)
    } else {
        onFailure(this)
    }
}

