package io.olaph.slack.client.test

interface MockMethod<Success, Failure, Params> {

    /**
     * set this value to mock a susccess response
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
