package com.kreait.slack.api.test

import com.kreait.slack.api.group.ApiCallMethod
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions

object MockMethodTestHelper {

    @Suppress("UNCHECKED_CAST")
    fun <Builder, Success, Failure, Params : Any> verify(methodProvider: () -> ApiCallMethod<Builder, Success, Failure, Params>,
                                                         successFunction: (Success?) -> Any,
                                                         successResponse: Success,
                                                         failureFunction: (Failure?) -> Any,
                                                         failureResponse: Failure,
                                                         params: Params) {


        val method = methodProvider.invoke()

        Assertions.assertTrue(method is MockMethod<*, *, *>)

        // setup method with

        method.with(params)


        // Set Failure Response
        (method as MockMethod<Success, Failure, Params>).successResponse = null
        (method as MockMethod<Success, Failure, Params>).failureResponse = failureResponse
        // Set Failure Function
        method.onSuccess(null)
        method.onFailure(failureFunction)
        methodProvider.invoke().invoke()
        // Verify that failure function has been called once after invocation
        verify(failureFunction, times(1)).invoke(failureResponse)
        // Remove failure function
        method.onFailure(null)
        val failureResult = methodProvider.invoke().invoke()
        // Verify that function has still been called online once after invocation
        verify(failureFunction, times(1)).invoke(failureResponse)
        // Assert that failure response equal
        Assertions.assertEquals(failureResponse, failureResult.failure, "FailureResponse is equal")

        // Set Success Response
        (method as MockMethod<Success, Failure, Params>).successResponse = successResponse
        (method as MockMethod<Success, Failure, Params>).failureResponse = null
        // Set Success Function
        method.onSuccess(successFunction)
        method.onFailure(null)
        methodProvider.invoke().invoke()
        // Verify that function has been called once after invocation
        verify(successFunction, times(1)).invoke(successResponse)
        // Remove failure function
        method.onSuccess(null)
        val successResult = methodProvider.invoke().invoke()
        // Verify that function has still been called online once after invocation
        verify(successFunction, times(1)).invoke(successResponse)
        Assertions.assertEquals(successResponse, successResult.success, "SuccessResponse is equal")

        Assertions.assertEquals(method.params(), params, "Params are equal")

    }
}
