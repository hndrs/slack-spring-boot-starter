package io.hndrs.slack.api.spring

import io.hndrs.slack.api.group.ApiCallMethod
import org.junit.jupiter.api.DynamicTest
import org.springframework.web.client.RestTemplate

object DynamicGroupTests {


    fun methodInvocations(testCases: List<MetaInfo>, mockTemplate: RestTemplate): List<DynamicTest> {
        return testCases.flatMap {
            listOf(
                    DynamicTest.dynamicTest("${it.endpoint} Failure Expectation") {
                        failure(it.endpoint, it.failure, it.params, it.method as io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Any>, mockTemplate)
                    },
                    DynamicTest.dynamicTest("${it.endpoint} Success Expectation") {
                        success(it.endpoint, it.success, it.params, it.method as io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Any>, mockTemplate)
                    }
            )
        }
    }


    private fun failure(methodEndpoint: String, response: Any, params: Any, method: io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Any>, mockTemplate: RestTemplate) {
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, methodEndpoint, response)
        val verifier = Verifier(response)

        ((method.with(params) as io.hndrs.slack.api.group.ApiCallMethod<*, *, *, out Any>)
                .onFailure { verifier.set(it) } as io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Any>)
                .invoke()


        mockServer.verify()
        verifier.verify()

    }

    private fun <Params : Any> success(methodEndpoint: String, response: Any, params: Params, method: io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Params>, mockTemplate: RestTemplate) {
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, methodEndpoint, response)
        val verifier = Verifier(response)

        ((method.with(params) as io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Params>)
                .onSuccess { verifier.set(it) } as io.hndrs.slack.api.group.ApiCallMethod<*, *, *, Params>)
                .invoke()


        mockServer.verify()
        verifier.verify()
    }

}

data class MetaInfo(val endpoint: String, val success: Any, val failure: Any, val params: Any, val method: io.hndrs.slack.api.group.ApiCallMethod<*, *, *, *>)
