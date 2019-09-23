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

class SpringConversationsMethodGroup : ConversationsMethodGroup {
    override fun close(authToken: String): ConversationsCloseMethod {
        return SpringConversationsCloseMethod(authToken)
    }

    override fun create(authToken: String): ConversationsCreateMethod {
        return SpringConversationsCreateMethod(authToken)
    }

    override fun history(authToken: String): ConversationsHistoryMethod {
        return SpringConversationsHistoryMethod(authToken)
    }

    override fun info(authToken: String): ConversationsInfoMethod {
        return SpringConversationsInfoMethod(authToken)
    }

    override fun invite(authToken: String): ConversationsInviteMethod {
        return SpringConversationsInviteMethod(authToken)
    }

    override fun join(authToken: String): ConversationsJoinMethod {
        return SpringConversationsJoinMethod(authToken)
    }

    override fun kick(authToken: String): ConversationsKickMethod {
        return SpringConversationsKickMethod(authToken)
    }

    override fun leave(authToken: String): ConversationsLeaveMethod {
        return SpringConversationsLeaveMethod(authToken)
    }

    override fun rename(authToken: String): ConversationsRenameMethod {
        return SpringConversationsRenameMethod(authToken)
    }

    override fun replies(authToken: String): ConversationsRepliesMethod {
        return SpringConversationsRepliesMethod(authToken)
    }

    override fun setPurpose(authToken: String): ConversationsSetPurposeMethod {
        return SpringConversationsSetPurposeMethod(authToken)
    }

    override fun setTopic(authToken: String): ConversationsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ConversationsUnarchiveMethod {
        return SpringConversationsUnarchiveMethod(authToken)
    }

    override fun archive(authToken: String): ConversationsArchiveMethod {
        return SpringConversationsArchiveMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(SpringConversationsMethodGroup::class.java)
    }

    override fun members(authToken: String): SpringConversationsMembersMethod {
        return SpringConversationsMembersMethod(authToken)
    }

    override fun open(authToken: String): SpringConversationsOpenMethod {
        return SpringConversationsOpenMethod(authToken)
    }

    override fun list(authToken: String): SpringConversationsListMethod {
        return SpringConversationsListMethod(authToken)
    }
}
