package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.conversations.ConversationsArchiveMethod
import io.olaph.slack.client.group.conversations.ConversationsCloseMethod
import io.olaph.slack.client.group.conversations.ConversationsCreateMethod
import io.olaph.slack.client.group.conversations.ConversationsHistoryMethod
import io.olaph.slack.client.group.conversations.ConversationsInfoMethod
import io.olaph.slack.client.group.conversations.ConversationsInviteMethod
import io.olaph.slack.client.group.conversations.ConversationsJoinMethod
import io.olaph.slack.client.group.conversations.ConversationsKickMethod
import io.olaph.slack.client.group.conversations.ConversationsLeaveMethod
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsRenameMethod
import io.olaph.slack.client.group.conversations.ConversationsRepliesMethod
import io.olaph.slack.client.group.conversations.ConversationsSetPurposeMethod
import io.olaph.slack.client.group.conversations.ConversationsSetTopicMethod
import io.olaph.slack.client.group.conversations.ConversationsUnarchiveMethod
import org.slf4j.LoggerFactory

class DefaultConversationsMethodGroup : ConversationsMethodGroup {
    override fun close(authToken: String): ConversationsCloseMethod {
        return DefaultConversationsCloseMethod(authToken)
    }

    override fun create(authToken: String): ConversationsCreateMethod {
        return DefaultConversationsCreateMethod(authToken)
    }

    override fun history(authToken: String): ConversationsHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String): ConversationsInfoMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invite(authToken: String): ConversationsInviteMethod {
        return DefaultConversationsInviteMethod(authToken)
    }

    override fun join(authToken: String): ConversationsJoinMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kick(authToken: String): ConversationsKickMethod {
        return DefaultConverstationsKickMethod(authToken)
    }

    override fun leave(authToken: String): ConversationsLeaveMethod {
        return DefaultConversationsLeaveMethod(authToken)
    }

    override fun rename(authToken: String): ConversationsRenameMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): ConversationsRepliesMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String): ConversationsSetPurposeMethod {
        return DefaultConversationsSetPurposeMethod(authToken)
    }

    override fun setTopic(authToken: String): ConversationsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ConversationsUnarchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun archive(authToken: String): ConversationsArchiveMethod {
        return DefaultConversationsArchiveMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultConversationsMethodGroup::class.java)
    }

    override fun members(authToken: String): DefaultConversationsMembersMethod {
        return DefaultConversationsMembersMethod(authToken)
    }

    override fun open(authToken: String): DefaultConversationsOpenMethod {
        return DefaultConversationsOpenMethod(authToken)
    }

    override fun list(authToken: String): DefaultConversationsListMethod {
        return DefaultConversationsListMethod(authToken)
    }
}
