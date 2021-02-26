package io.hndrs.slack.api.contract.jackson.util

import java.time.Instant

/**
 * Helper extension that transforms an Instant to a slack timestamp
 */
fun Instant.toSlackTimestampString(): String {
    return if (this.micros() == 0L) {
        "${this.epochSecond}"
    } else {
        val paddedMicros = "${this.micros()}".padStart(6, '0')
        "${this.epochSecond}.$paddedMicros"
    }
}

fun String.toSlackTimeStampInstant(): Instant {
    val parts = this.split(".").map { it.toLong() }
    return if (parts.size == 1) {
        Instant.ofEpochSecond(parts[0])
    } else {
        Instant.ofEpochSecond(parts[0], parts[1] * 1000)
    }
}

fun Instant.micros(): Long {
    return (this.nano / 1e3).toLong()
}

