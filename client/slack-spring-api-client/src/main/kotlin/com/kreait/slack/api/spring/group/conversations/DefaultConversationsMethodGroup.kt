package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.group.conversations.ConversationsArchiveMethod
import com.kreait.slack.api.group.conversations.ConversationsCloseMethod
import com.kreait.slack.api.group.conversations.ConversationsCreateMethod
import com.kreait.slack.api.group.conversations.ConversationsHistoryMethod
import com.kreait.slack.api.group.conversations.ConversationsInfoMethod
import com.kreait.slack.api.group.conversations.ConversationsInviteMethod
import com.kreait.slack.api.group.conversations.ConversationsJoinMethod
import com.kreait.slack.api.group.conversations.ConversationsKickMethod
import com.kreait.slack.api.group.conversations.ConversationsLeaveMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsRenameMethod
import com.kreait.slack.api.group.conversations.ConversationsRepliesMethod
import com.kreait.slack.api.group.conversations.ConversationsSetPurposeMethod
import com.kreait.slack.api.group.conversations.ConversationsSetTopicMethod
import com.kreait.slack.api.group.conversations.ConversationsUnarchiveMethod
import org.slf4j.LoggerFactory

class DefaultConversationsMethodGroup : ConversationsMethodGroup {
    override fun close(authToken: String): ConversationsCloseMethod {
        return DefaultConversationsCloseMethod(authToken)
    }

    override fun create(authToken: String): ConversationsCreateMethod {
        return DefaultConversationsCreateMethod(authToken)
    }

    override fun history(authToken: String): ConversationsHistoryMethod {
        return DefaultConversationsHistoryMethod(authToken)
    }

    override fun info(authToken: String): ConversationsInfoMethod {
        return DefaultConversationsInfoMethod(authToken)
    }

    override fun invite(authToken: String): ConversationsInviteMethod {
        return DefaultConversationsInviteMethod(authToken)
    }

    override fun join(authToken: String): ConversationsJoinMethod {
        return DefaultConversationsJoinMethod(authToken)
    }

    override fun kick(authToken: String): ConversationsKickMethod {
        return DefaultConversationsKickMethod(authToken)
    }

    override fun leave(authToken: String): ConversationsLeaveMethod {
        return DefaultConversationsLeaveMethod(authToken)
    }

    override fun rename(authToken: String): ConversationsRenameMethod {
        return DefaultConversationsRenameMethod(authToken)
    }

    override fun replies(authToken: String): ConversationsRepliesMethod {
        return DefaultConversationsRepliesMethod(authToken)
    }

    override fun setPurpose(authToken: String): ConversationsSetPurposeMethod {
        return DefaultConversationsSetPurposeMethod(authToken)
    }

    override fun setTopic(authToken: String): ConversationsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ConversationsUnarchiveMethod {
        return DefaultConversationsUnarchiveMethod(authToken)
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
