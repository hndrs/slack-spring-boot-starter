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
import com.kreait.slack.api.group.channels.ChannelsSetPurposeMethod
import com.kreait.slack.api.group.channels.ChannelsSetTopicMethod
import com.kreait.slack.api.group.channels.ChannelsUnarchiveMethod
import org.slf4j.LoggerFactory

class DefaultChannelsMethodGroup : ChannelsMethodGroup {

    override fun archive(authToken: String): ChannelsArchiveMethod {
        return DefaultChannelsArchiveMethod(authToken)
    }

    override fun create(authToken: String): ChannelsCreateMethod {
        return DefaultChannelsCreateMethod(authToken)
    }

    override fun history(authToken: String): ChannelsHistoryMethod {
        return DefaultChannelHistoryMethod(authToken)
    }

    override fun invite(authToken: String): ChannelsInviteMethod {
        return DefaultChannelsInviteMethod(authToken)
    }

    override fun join(authToken: String): ChannelsJoinMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kick(authToken: String): ChannelsKickMethod {
        return DefaultChannelsKickMethod(authToken)
    }

    override fun leave(authToken: String): ChannelsLeaveMethod {
        return DefaultChannelsLeaveMethod(authToken)
    }

    override fun mark(authToken: String): ChannelsMarkMethod {
        return DefaultChannelsMarkMethod(authToken)
    }

    override fun rename(authToken: String): ChannelsRenameMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): ChannelsRepliesMethod {
        return DefaultChannelsRepliesMethod(authToken)
    }

    override fun setPurpose(authToken: String): ChannelsSetPurposeMethod {
        return DefaultChannelsSetPurposeMethod(authToken)
    }

    override fun setTopic(authToken: String): ChannelsSetTopicMethod {
        return DefaultChannelsSetTopicMethod(authToken)
    }

    override fun unarchive(authToken: String): ChannelsUnarchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultChannelsMethodGroup::class.java)
    }

    override fun info(authToken: String): ChannelsInfoMethod {
        return DefaultChannelsInfoMethod(authToken)
    }

}
