package com.kreait.slack.api.test

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
