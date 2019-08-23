package com.kreait.slack.api.test.group.channels

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
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.kreait.slack.api.test.group.channel.MockChannelHistoryMethod
import com.kreait.slack.api.test.group.channel.MockChannelsArchiveMethod
import com.kreait.slack.api.test.group.channel.MockChannelsCreateMethod
import com.kreait.slack.api.test.group.channel.MockChannelsInfoMethod
import com.kreait.slack.api.test.group.channel.MockChannelsInviteMethod
import com.kreait.slack.api.test.group.channel.MockChannelsJoinMethod
import com.kreait.slack.api.test.group.channel.MockChannelsKickMethod
import com.kreait.slack.api.test.group.channel.MockChannelsLeaveMethod
import com.kreait.slack.api.test.group.channel.MockChannelsMarkMethod
import com.kreait.slack.api.test.group.channel.MockChannelsRenameMethod
import com.kreait.slack.api.test.group.channel.MockChannelsRepliesMethod
import com.kreait.slack.api.test.group.channel.MockChannelsSetPurposeMethod
import com.kreait.slack.api.test.group.channel.MockChannelsSetTopicMethod
import com.kreait.slack.api.test.group.channel.MockChannelsUnarchiveMethod
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockChannelGroupTests {
    @BeforeEach
    fun setup() {

    }

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockChannelHistoryMethod(), mock { }, SuccessfulChannelsHistoryResponse.sample(), mock { }, ErrorChannelsHistoryResponse.sample(), ChannelsHistoryRequest.sample()),
            MockMetaInfo(MockChannelsJoinMethod(), mock { }, SuccessfulChannelsJoinResponse.sample(), mock { }, ErrorChannelsJoinResponse.sample(), ChannelsJoinRequest.sample()),
            MockMetaInfo(MockChannelsKickMethod(), mock { }, SuccessfulChannelKickResponse.sample(), mock { }, ErrorChannelKickResponse.sample(), ChannelsKickRequest.sample()),
            MockMetaInfo(MockChannelsLeaveMethod(), mock { }, SuccessfulChannelsLeaveResponse.sample(), mock { }, ErrorChannelsLeaveResponse.sample(), ChannelsLeaveRequest.sample()),
            MockMetaInfo(MockChannelsArchiveMethod(), mock { }, SuccessfulChannelArchiveResponse.sample(), mock { }, ErrorChannelArchiveResponse.sample(), ChannelsArchiveRequest.sample()),
            MockMetaInfo(MockChannelsCreateMethod(), mock { }, SuccessfulChannelsCreateResponse.sample(), mock { }, ErrorChannelsCreateResponse.sample(), ChannelsCreateRequest.sample()),
            MockMetaInfo(MockChannelsInfoMethod(), mock { }, SuccessfulChannelInfoResponse.sample(), mock { }, ErrorChannelInfoResponse.sample(), ChannelsInfoRequest.sample()),
            MockMetaInfo(MockChannelsInviteMethod(), mock { }, SuccessfulChannelInviteResponse.sample(), mock { }, ErrorChannelInviteResponse.sample(), ChannelInviteRequest.sample()),
            MockMetaInfo(MockChannelsMarkMethod(), mock { }, SuccessfulChannelsMarkResponse.sample(), mock { }, ErrorChannelsMarkResponse.sample(), ChannelsMarkRequest.sample()),
            MockMetaInfo(MockChannelsRenameMethod(), mock { }, SuccessfulChannelRenameResponse.sample(), mock { }, ErrorChannelRenameResponse.sample(), ChannelRenameRequest.sample()),
            MockMetaInfo(MockChannelsRepliesMethod(), mock { }, SuccessfulChannelsRepliesResponse.sample(), mock { }, ErrorChannelsRepliesResponse.sample(), ChannelsRepliesRequest.sample()),
            MockMetaInfo(MockChannelsSetPurposeMethod(), mock { }, SuccessfulChannelsSetPurposeResponse.sample(), mock { }, ErrorChannelsSetPurposeResponse.sample(), ChannelsSetPurposeRequest.sample()),
            MockMetaInfo(MockChannelsSetTopicMethod(), mock { }, SuccessfulChannelsSetTopicResponse.sample(), mock { }, ErrorChannelsSetTopicResponse.sample(), ChannelsSetTopicRequest.sample()),
            MockMetaInfo(MockChannelsUnarchiveMethod(), mock { }, SuccessfulChannelUnarchiveResponse.sample(), mock { }, ErrorChannelUnarchiveResponse.sample(), ChannelsUnarchiveRequest.sample())
    )
}