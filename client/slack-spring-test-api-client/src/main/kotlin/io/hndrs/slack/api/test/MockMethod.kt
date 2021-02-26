package io.hndrs.slack.api.test

/**
 * Interface which holds responses to it for testing
 *
 * @param Success the success response of the method
 * @param Failure the failure response of the method
 * @param Params the request parameters of the method
 */
interface MockMethod<Success, Failure, Params> {

    /**
     * set this value to mock a success response
     */
    var successResponse: Success?

    /**
     * set this value to mock a failure response
     */
    var failureResponse: Failure?

    /**
     * get the params that have been given to the client
     */
    fun params(): Params

}
