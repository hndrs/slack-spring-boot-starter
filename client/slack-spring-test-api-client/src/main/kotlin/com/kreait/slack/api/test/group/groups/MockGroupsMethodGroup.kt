package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.group.conversations.ConversationsArchiveMethod
import com.kreait.slack.api.group.conversations.ConversationsCloseMethod
import com.kreait.slack.api.group.conversations.ConversationsCreateMethod
import com.kreait.slack.api.group.conversations.ConversationsHistoryMethod
import com.kreait.slack.api.group.conversations.ConversationsInfoMethod
import com.kreait.slack.api.group.conversations.ConversationsInviteMethod
import com.kreait.slack.api.group.conversations.ConversationsJoinMethod
import com.kreait.slack.api.group.conversations.ConversationsKickMethod
import com.kreait.slack.api.group.conversations.ConversationsLeaveMethod
import com.kreait.slack.api.group.conversations.ConversationsListMethod
import com.kreait.slack.api.group.conversations.ConversationsMembersMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsOpenMethod
import com.kreait.slack.api.group.conversations.ConversationsRenameMethod
import com.kreait.slack.api.group.conversations.ConversationsRepliesMethod
import com.kreait.slack.api.group.conversations.ConversationsSetPurposeMethod
import com.kreait.slack.api.group.conversations.ConversationsSetTopicMethod
import com.kreait.slack.api.group.conversations.ConversationsUnarchiveMethod

class MockGroupsMethodGroup : ConversationsMethodGroup {
    override fun archive(authToken: String): ConversationsArchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

}
