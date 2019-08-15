package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.group.channels.ChannelsArchiveMethod
import com.kreait.slack.api.group.channels.ChannelsCreateMethod
import com.kreait.slack.api.group.channels.ChannelsHistoryMethod
import com.kreait.slack.api.group.channels.ChannelsInfoMethod
import com.kreait.slack.api.group.channels.ChannelsInviteMethod
import com.kreait.slack.api.group.channels.ChannelsJoinMethod
import com.kreait.slack.api.group.channels.ChannelsKickMethod
import com.kreait.slack.api.group.channels.ChannelsLeaveMethod
import com.kreait.slack.api.group.channels.ChannelsMarkMethod
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.channels.ChannelsRenameMethod
import com.kreait.slack.api.group.channels.ChannelsRepliesMethod
import com.kreait.slack.api.group.channels.ChannelsUnarchiveMethod
import com.kreait.slack.api.group.channels.SetChannelsPurposeMethod
import com.kreait.slack.api.group.channels.SetChannelsTopicMethod
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
