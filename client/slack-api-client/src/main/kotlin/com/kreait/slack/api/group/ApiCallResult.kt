package com.kreait.slack.api.group


data class ApiCallResult<out Success, out Failure>(
        val success: Success? = null,
        val failure: Failure? = null
) {
    fun wasSuccess(): Boolean = success != null

    fun wasFailure(): Boolean = failure != null
}
