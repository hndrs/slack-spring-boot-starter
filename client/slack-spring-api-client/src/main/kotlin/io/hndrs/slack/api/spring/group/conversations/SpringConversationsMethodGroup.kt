package io.hndrs.slack.api.spring.group.conversations

import io.hndrs.slack.api.group.conversations.ConversationsArchiveMethod
import io.hndrs.slack.api.group.conversations.ConversationsCloseMethod
import io.hndrs.slack.api.group.conversations.ConversationsCreateMethod
import io.hndrs.slack.api.group.conversations.ConversationsHistoryMethod
import io.hndrs.slack.api.group.conversations.ConversationsInfoMethod
import io.hndrs.slack.api.group.conversations.ConversationsInviteMethod
import io.hndrs.slack.api.group.conversations.ConversationsJoinMethod
import io.hndrs.slack.api.group.conversations.ConversationsKickMethod
import io.hndrs.slack.api.group.conversations.ConversationsLeaveMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsRenameMethod
import io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod
import io.hndrs.slack.api.group.conversations.ConversationsSetPurposeMethod
import io.hndrs.slack.api.group.conversations.ConversationsSetTopicMethod
import io.hndrs.slack.api.group.conversations.ConversationsUnarchiveMethod
import org.slf4j.LoggerFactory

/**
 * Convenience function to apply slack api conversation method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
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

    @Suppress("NotImplementedDeclaration")
    override fun setTopic(authToken: String): ConversationsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ConversationsUnarchiveMethod {
        return SpringConversationsUnarchiveMethod(authToken)
    }

    override fun archive(authToken: String): ConversationsArchiveMethod {
        return SpringConversationsArchiveMethod(authToken)
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
