package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.conversations.ConversationsArchiveMethod
import io.olaph.slack.client.group.conversations.ConversationsCloseMethod
import io.olaph.slack.client.group.conversations.ConversationsCreateMethod
import io.olaph.slack.client.group.conversations.ConversationsHistoryMethod
import io.olaph.slack.client.group.conversations.ConversationsInfoMethod
import io.olaph.slack.client.group.conversations.ConversationsInviteMethod
import io.olaph.slack.client.group.conversations.ConversationsJoinMethod
import io.olaph.slack.client.group.conversations.ConversationsKickMethod
import io.olaph.slack.client.group.conversations.ConversationsLeaveMethod
import io.olaph.slack.client.group.conversations.ConversationsListMethod
import io.olaph.slack.client.group.conversations.ConversationsMembersMethod
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod
import io.olaph.slack.client.group.conversations.ConversationsRenameMethod
import io.olaph.slack.client.group.conversations.ConversationsRepliesMethod
import io.olaph.slack.client.group.conversations.ConversationsSetPurposeMethod
import io.olaph.slack.client.group.conversations.ConversationsSetTopicMethod
import io.olaph.slack.client.group.conversations.ConversationsUnarchiveMethod


class MockConversationMethodGroup : ConversationsMethodGroup {
    override fun close(authToken: String): ConversationsCloseMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(authToken: String): ConversationsCreateMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun history(authToken: String): ConversationsHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String): ConversationsInfoMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invite(authToken: String): ConversationsInviteMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun join(authToken: String): ConversationsJoinMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kick(authToken: String): ConversationsKickMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leave(authToken: String): ConversationsLeaveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String): ConversationsListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun members(authToken: String): ConversationsMembersMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun open(authToken: String): ConversationsOpenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rename(authToken: String): ConversationsRenameMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): ConversationsRepliesMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String): ConversationsSetPurposeMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTopic(authToken: String): ConversationsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ConversationsUnarchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun archive(authToken: String): ConversationsArchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}