package io.olaph.slack.client.group

@Suppress("UNCHECKED_CAST")
abstract class ApiCallMethod<Builder, Success, Failure, Params : Any> {

    protected var onFailure: ((Failure) -> Any)? = null
    protected var onSuccess: ((Success) -> Any)? = null
    protected lateinit var params: Params

    fun with(params: Params): Builder {
        this.params = params
        return this as Builder
    }

    fun onSuccess(onSuccess: ((Success) -> Any)?): Builder {
        this.onSuccess = onSuccess
        return this as Builder
    }

    fun onFailure(onFailure: ((Failure) -> Any)?): Builder {
        this.onFailure = onFailure
        return this as Builder
    }


    abstract fun request(): ApiCallResult<Success, Failure>

    fun invoke(): ApiCallResult<Success, Failure> {
        return request()
    }


}
