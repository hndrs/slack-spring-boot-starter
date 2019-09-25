package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.group.channels.ChannelsMethodGroup

class MockChannelsMethodGroup : ChannelsMethodGroup {

    private val mockChannelsInfoMethod = MockChannelsInfoMethod()
    private val mockChannelsArchiveMethod = MockChannelsArchiveMethod()
    private val mockChannelsCreateMethod = MockChannelsCreateMethod()
    private val mockChannelsInviteMethod = MockChannelsInviteMethod()
    private val mockChannelsSetPurposeMethod = MockChannelsSetPurposeMethod()
    private val mockChannelKickMethod = MockChannelsKickMethod()
    private val mockChannelsUnarchiveMethod = MockChannelsUnarchiveMethod()
    private val mockChannelsJoinMethod = MockChannelsJoinMethod()
    private val mockChannelsRenameMethod = MockChannelsRenameMethod()
    private val mockChannelsSetTopicMethod = MockChannelsSetTopicMethod()
    private val mockChannelsMarkMethod = MockChannelsMarkMethod()
    private val mockChannelsRepliesMethod = MockChannelsRepliesMethod()
    private val mockChannelsLeaveMethod = MockChannelsLeaveMethod()
    private val mockChannelsHistoryMethod = MockChannelHistoryMethod()

    override fun archive(authToken: String) = mockChannelsArchiveMethod
    override fun create(authToken: String) = mockChannelsCreateMethod
    override fun history(authToken: String) = mockChannelsHistoryMethod
    override fun info(authToken: String) = mockChannelsInfoMethod
    override fun invite(authToken: String) = mockChannelsInviteMethod
    override fun join(authToken: String) = mockChannelsJoinMethod
    override fun kick(authToken: String) = mockChannelKickMethod
    override fun leave(authToken: String) = mockChannelsLeaveMethod
    override fun mark(authToken: String) = mockChannelsMarkMethod
    override fun rename(authToken: String) = mockChannelsRenameMethod
    override fun replies(authToken: String) = mockChannelsRepliesMethod
    override fun setPurpose(authToken: String) = mockChannelsSetPurposeMethod
    override fun setTopic(authToken: String) = mockChannelsSetTopicMethod
    override fun unarchive(authToken: String) = mockChannelsUnarchiveMethod
}
