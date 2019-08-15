package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.group.channels.ChannelsHistoryMethod
import com.kreait.slack.api.group.channels.ChannelsKickMethod
import com.kreait.slack.api.group.channels.ChannelsLeaveMethod
import com.kreait.slack.api.group.channels.ChannelsMarkMethod
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.channels.ChannelsRepliesMethod
import com.kreait.slack.api.group.channels.ChannelsSetPurposeMethod
import com.kreait.slack.api.group.channels.ChannelsUnarchiveMethod

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

    override fun archive(authToken: String): MockChannelsArchiveMethod {
        return mockChannelsArchiveMethod
    }

    override fun create(authToken: String): MockChannelsCreateMethod {
        return mockChannelsCreateMethod
    }

    override fun history(authToken: String): ChannelsHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String): MockChannelsInfoMethod {
        return mockChannelsInfoMethod
    }

    override fun invite(authToken: String): MockChannelsInviteMethod {
        return mockChannelsInviteMethod
    }

    override fun join(authToken: String): MockChannelsJoinMethod {
        return mockChannelsJoinMethod
    }

    override fun kick(authToken: String): ChannelsKickMethod {
        return mockChannelKickMethod
    }

    override fun leave(authToken: String): ChannelsLeaveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mark(authToken: String): ChannelsMarkMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rename(authToken: String): MockChannelsRenameMethod {
        return mockChannelsRenameMethod
    }

    override fun replies(authToken: String): ChannelsRepliesMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String): ChannelsSetPurposeMethod {
        return mockChannelsSetPurposeMethod
    }

    override fun setTopic(authToken: String): MockChannelsSetTopicMethod {
        return mockChannelsSetTopicMethod
    }

    override fun unarchive(authToken: String): MockChannelsUnarchiveMethod {
        return mockChannelsUnarchiveMethod
    }
}
