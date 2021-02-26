package io.hndrs.slack.api.spring.group.users

import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.common.types.sample
import io.hndrs.slack.api.contract.jackson.group.users.ErrorListAllResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.users.ListAllRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListResponse
import io.hndrs.slack.api.contract.jackson.group.users.sample
import io.hndrs.slack.api.spring.MockServerHelper
import io.hndrs.slack.api.spring.Verifier
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SpringUserListAllMethodTest {

    @Test
    fun testSuccess() {
        val method = SpringUserListAllMethod("")

        val member1 = Member.sample().copy(id = "test1")
        val member2 = Member.sample().copy(id = "test2")
        val allResponse = SuccessfulListAllResponse(listOf(member1, member2))
        val verifier = Verifier(allResponse)
        val params = ListAllRequest(true, 1)

        val mockServer = MockServerHelper.buildMockRestServer(
            io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate(),
                "users.list", SuccessfulListResponse(true, listOf(member1), 0, ResponseMetadata("test")),
                SuccessfulListResponse(true, listOf(member2), 0, ResponseMetadata("")))

        method.with(params)
                .onSuccess {
                    verifier.set(it)
                    Assertions.assertEquals(2, it.members.size)
                }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    fun testFailure() {
        val method = SpringUserListAllMethod("")
        val allResponse = ErrorListAllResponse.sample()
        val verifier = Verifier(allResponse)
        val params = ListAllRequest(true, 1)
        val mockServer = MockServerHelper.buildMockRestServer(
            io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate(),
                "users.list", ErrorListResponse.sample())

        method.with(params)
                .onFailure {
                    verifier.set(it)
                }.invoke()
        mockServer.verify()
        verifier.verify()
    }
}
