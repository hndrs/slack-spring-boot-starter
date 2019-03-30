package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.group.channels.ChannelsArchiveMethod
import io.olaph.slack.client.group.channels.ChannelsCreateMethod
import io.olaph.slack.client.group.channels.ChannelsHistoryMethod
import io.olaph.slack.client.group.channels.ChannelsInfoMethod
import io.olaph.slack.client.group.channels.ChannelsInviteMethod
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
import org.slf4j.LoggerFactory

class DefaultChannelMethodGroup : ChannelsMethodGroup {

    override fun archive(authToken: String): ChannelsArchiveMethod {
        return DefaultChannelsArchiveMethod(authToken)
    }

    override fun create(authToken: String): ChannelsCreateMethod {
        return DefaultChannelCreateMethod(authToken)
    }

    override fun history(authToken: String): ChannelsHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invite(authToken: String): ChannelsInviteMethod {
        return DefaultChannelInviteMethod(authToken)
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

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultChannelMethodGroup::class.java)
    }

    override fun info(authToken: String): ChannelsInfoMethod {
        return DefaultGetChannelInfoMethod(authToken)
    }

}
