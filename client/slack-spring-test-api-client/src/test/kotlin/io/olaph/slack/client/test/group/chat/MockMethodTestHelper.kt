import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.client.test.MockMethod
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions

object MockMethodTestHelper {

    fun <Builder, Success, Failure, Params : Any> verify(method: ApiCallMethod<Builder, Success, Failure, Params>,
                                                         successFunction: (Success?) -> Any,
                                                         successResponse: Success,
                                                         failureFunction: (Failure?) -> Any,
                                                         failureResponse: Failure,
                                                         params: Params) {

        Assertions.assertTrue(method is MockMethod<*, *, *>)

        // setup method with
        method.onSuccess(successFunction)
        method.onFailure(failureFunction)
        method.with(params)

        (method as MockMethod<Success, Failure, Params>).successResponse = successResponse
        (method as MockMethod<Success, Failure, Params>).failureResponse = failureResponse

        // call method
        val request = method.request()

        // verify
        verify(successFunction, times(1)).invoke(successResponse)
        verify(failureFunction, times(1)).invoke(failureResponse)

        Assertions.assertEquals(method.params(), params, "Params are equal")
        Assertions.assertEquals(successResponse, request.success, "SuccessResponse is equal")
        Assertions.assertEquals(failureResponse, request.failure, "FailureResponse is equal")
    }
}
