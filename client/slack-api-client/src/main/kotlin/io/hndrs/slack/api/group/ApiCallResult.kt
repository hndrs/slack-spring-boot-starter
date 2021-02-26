package io.hndrs.slack.api.group


/**
 * Data representation of an api call result
 * Success will be the successful response from the slack api
 * Failure will be the failure response from the slack api
 *
 * Note:
 * This class is not representing http errors (like Http 500)
 */
data class ApiCallResult<out Success, out Failure>(
    val success: Success? = null,
    val failure: Failure? = null
) {
    /**
     * @return true when api call was successful
     */
    fun wasSuccess(): Boolean = success != null

    /**
     * @return true when api call operation failed
     */
    fun wasFailure(): Boolean = failure != null
}
