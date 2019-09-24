package com.kreait.slack.api.test

import com.kreait.slack.api.group.ApiCallMethod
import org.junit.jupiter.api.DynamicTest

object DynamicMockGroupTests {

    fun methodInvocations(testCases: List<MockMetaInfo>): List<DynamicTest> {
        return testCases.flatMap {
            listOf(
                    DynamicTest.dynamicTest("Test ${it.methodProvider.javaClass.canonicalName}") {
                        test(it)
                    }
            )
        }
    }


    private fun test(it: MockMetaInfo) {
        MockMethodTestHelper.verify(it.methodProvider as () -> ApiCallMethod<Any,Any,Any,Any>, it.successFunction, it.successResponse, it.failureFunction, it.failureResponse, it.params)

    }
}


data class MockMetaInfo(val methodProvider: () -> Any, val successFunction: (Any?) -> Any, val successResponse: Any, val failureFunction: (Any?) -> Any,
                        val failureResponse: Any, val params: Any)
