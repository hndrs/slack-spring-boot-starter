package com.kreait.slack.api.spring

import com.kreait.slack.api.group.ApiCallMethod
import org.junit.jupiter.api.DynamicTest
import org.springframework.web.client.RestTemplate

object DynamicGroupTests {


    fun methodInvocations(testCases: List<MetaInfo>, mockTemplate: RestTemplate): List<DynamicTest> {
        return testCases.flatMap {
            listOf(
                    DynamicTest.dynamicTest("${it.endpoint} Failure Expectation") {
                        failure(it.endpoint, it.failure, it.params, it.method as ApiCallMethod<*, *, *, Any>, mockTemplate)
                    },
                    DynamicTest.dynamicTest("${it.endpoint} Success Expectation") {
                        success(it.endpoint, it.success, it.params, it.method as ApiCallMethod<*, *, *, Any>, mockTemplate)
                    }
            )
        }
    }


    private fun failure(methodEndpoint: String, response: Any, params: Any, method: ApiCallMethod<*, *, *, Any>, mockTemplate: RestTemplate) {
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, methodEndpoint, response)
        val verifier = Verifier(response)

        ((method.with(params) as ApiCallMethod<*, *, *, out Any>)
                .onFailure { verifier.set(it) } as ApiCallMethod<*, *, *, Any>)
                .invoke()


        mockServer.verify()
        verifier.verify()

    }

    private fun <Params : Any> success(methodEndpoint: String, response: Any, params: Params, method: ApiCallMethod<*, *, *, Params>, mockTemplate: RestTemplate) {
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, methodEndpoint, response)
        val verifier = Verifier(response)

        ((method.with(params) as ApiCallMethod<*, *, *, Params>)
                .onSuccess { verifier.set(it) } as ApiCallMethod<*, *, *, Params>)
                .invoke()


        mockServer.verify()
        verifier.verify()
    }

}

data class MetaInfo(val endpoint: String, val success: Any, val failure: Any, val params: Any, val method: ApiCallMethod<*, *, *, *>)
