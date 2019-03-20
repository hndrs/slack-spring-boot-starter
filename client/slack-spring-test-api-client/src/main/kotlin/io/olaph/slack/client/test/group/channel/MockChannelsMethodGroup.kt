package io.olaph.slack.client.test.group.channel

import io.olaph.slack.client.group.channels.ChannelsHistoryMethod
import io.olaph.slack.client.group.channels.ChannelsJoinMethod
import io.olaph.slack.client.group.channels.ChannelsKickMethod
import io.olaph.slack.client.group.channels.ChannelsLeaveMethod
import io.olaph.slack.client.group.channels.ChannelsListMethod
import io.olaph.slack.client.group.channels.ChannelsMarkMethod
import io.olaph.slack.client.group.channels.ChannelsMethodGroup
import io.olaph.slack.client.group.channels.ChannelsRenameMethod
import io.olaph.slack.client.group.channels.ChannelsRepliesMethod
import io.olaph.slack.client.group.channels.ChannelsUnarchiveMethod
import io.olaph.slack.client.group.channels.SetChannelsPurposeMethod
import io.olaph.slack.client.group.channels.SetChannelsTopicMethod

class MockChannelsMethodGroup : ChannelsMethodGroup {

    private val mockChannelsInfoMethod = MockChannelsInfoMethod()
    private val mockChannelsArchiveMethod = MockChannelsArchiveMethod()
    private val mockChannelsCreateMethod = MockChannelsCreateMethod()
    private val mockChannelsInviteMethod = MockChannelsInviteMethod()

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

    override fun join(authToken: String): ChannelsJoinMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kick(authToken: String): ChannelsKickMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leave(authToken: String): ChannelsLeaveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String): ChannelsListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mark(authToken: String): ChannelsMarkMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rename(authToken: String): ChannelsRenameMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): ChannelsRepliesMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String): SetChannelsPurposeMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTopic(authToken: String): SetChannelsTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ChannelsUnarchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}