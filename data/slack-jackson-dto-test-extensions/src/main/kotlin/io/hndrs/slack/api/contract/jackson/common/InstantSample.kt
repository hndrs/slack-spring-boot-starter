package io.hndrs.slack.api.contract.jackson.common

import java.time.Instant

object InstantSample {

    private val SAMPLE = Instant.ofEpochSecond(1565884788)

    fun sample(): Instant {
        return SAMPLE
    }
}
