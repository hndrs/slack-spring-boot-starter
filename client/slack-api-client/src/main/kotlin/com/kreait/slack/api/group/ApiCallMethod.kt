package com.kreait.slack.api.group

@Suppress("UNCHECKED_CAST")
abstract class ApiCallMethod<Builder, Success, Failure, Params : Any> {

    protected var onFailure: ((Failure) -> Any)? = null
    protected var onSuccess: ((Success) -> Any)? = null
    protected lateinit var params: Params

    /**
     * @param params the payload type of implementing class
     * @return this
     */
    fun with(params: Params): Builder {
        this.params = params
        return this as Builder
    }

    /**
     * @param onSuccess the function that will be invoked on a successful api response
     * @return this
     */
    fun onSuccess(onSuccess: ((Success) -> Any)?): Builder {
        this.onSuccess = onSuccess
        return this as Builder
    }

    /**
     * @param onFailure the function that will be invoked on a failing api response (not including network issues)
     * @return this
     */
    fun onFailure(onFailure: ((Failure) -> Any)?): Builder {
        this.onFailure = onFailure
        return this as Builder
    }


    /**
     * execution implementation of the request to the slack api
     */
    protected abstract fun request(): ApiCallResult<Success, Failure>

    /**
     * api call invocation
     */
    fun invoke(): ApiCallResult<Success, Failure> {
        return request()
    }


}
