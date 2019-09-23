package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelRenameRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsJoinRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsKickRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("Channels Tests")
class MethodsTests {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("channels.kick", SuccessfulChannelKickResponse.sample(), ErrorChannelKickResponse.sample(), ChannelsKickRequest.sample(), SpringChannelsKickMethod("", mockTemplate)),
            MetaInfo("channels.mark", SuccessfulChannelsMarkResponse.sample(), ErrorChannelsMarkResponse.sample(), ChannelsMarkRequest.sample(), SpringChannelsMarkMethod("", mockTemplate)),
            MetaInfo("channels.archive", SuccessfulChannelArchiveResponse.sample(), ErrorChannelArchiveResponse.sample(), ChannelsArchiveRequest.sample(), SpringChannelsArchiveMethod("", mockTemplate)),
            MetaInfo("channels.create", SuccessfulChannelsCreateResponse.sample(), ErrorChannelsCreateResponse.sample(), ChannelsCreateRequest.sample(), SpringChannelsCreateMethod("", mockTemplate)),
            MetaInfo("channels.history", SuccessfulChannelsHistoryResponse.sample(), ErrorChannelsHistoryResponse.sample(), ChannelsHistoryRequest.sample(), SpringChannelsHistoryMethod("", mockTemplate)),
            MetaInfo("channels.history", SuccessfulChannelsHistoryResponse.sample(), ErrorChannelsHistoryResponse.sample(), ChannelsHistoryRequest.sample(), SpringChannelsHistoryMethod("", mockTemplate)),
            MetaInfo("channels.info", SuccessfulChannelInfoResponse.sample(), ErrorChannelInfoResponse.sample(), ChannelsInfoRequest.sample(), SpringChannelsInfoMethod("", mockTemplate)),
            MetaInfo("channels.invite", SuccessfulChannelInviteResponse.sample(), ErrorChannelInviteResponse.sample(), ChannelInviteRequest.sample(), SpringChannelsInviteMethod("", mockTemplate)),
            MetaInfo("channels.join", SuccessfulChannelsJoinResponse.sample(), ErrorChannelsJoinResponse.sample(), ChannelsJoinRequest.sample(), SpringChannelsJoinMethod("", mockTemplate)),
            MetaInfo("channels.leave", SuccessfulChannelsLeaveResponse.sample(), ErrorChannelsLeaveResponse.sample(), ChannelsLeaveRequest.sample(), SpringChannelsLeaveMethod("", mockTemplate)),
            MetaInfo("channels.rename", SuccessfulChannelRenameResponse.sample(), ErrorChannelRenameResponse.sample(), ChannelRenameRequest.sample(), SpringChannelsRenameMethod("", mockTemplate)),
            MetaInfo("channels.replies", SuccessfulChannelsRepliesResponse.sample(), ErrorChannelsRepliesResponse.sample(), ChannelsRepliesRequest.sample(), SpringChannelsRepliesMethod("", mockTemplate)),
            MetaInfo("channels.setPurpose", SuccessfulChannelsSetPurposeResponse.sample(), ErrorChannelsSetPurposeResponse.sample(), ChannelsSetPurposeRequest.sample(), SpringChannelsSetPurposeMethod("", mockTemplate)),
            MetaInfo("channels.setTopic", SuccessfulChannelsSetTopicResponse.sample(), ErrorChannelsSetTopicResponse.sample(), ChannelsSetTopicRequest.sample(), SpringChannelsSetTopicMethod("", mockTemplate)),
            MetaInfo("channels.unarchive", SuccessfulChannelUnarchiveResponse.sample(), ErrorChannelUnarchiveResponse.sample(), ChannelsUnarchiveRequest.sample(), SpringChannelsUnarchiveMethod("", mockTemplate))
    )
}
