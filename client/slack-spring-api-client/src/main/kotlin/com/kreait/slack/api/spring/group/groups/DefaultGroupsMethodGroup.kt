package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.group.groups.GroupsArchiveMethod
import com.kreait.slack.api.group.groups.GroupsCreateChildMethod
import com.kreait.slack.api.group.groups.GroupsCreateMethod
import com.kreait.slack.api.group.groups.GroupsHistoryMethod
import com.kreait.slack.api.group.groups.GroupsInfoMethod
import com.kreait.slack.api.group.groups.GroupsInviteMethod
import com.kreait.slack.api.group.groups.GroupsKickMethod
import com.kreait.slack.api.group.groups.GroupsLeaveMethod
import com.kreait.slack.api.group.groups.GroupsListMethod
import com.kreait.slack.api.group.groups.GroupsMarkMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.groups.GroupsOpenMethod
import com.kreait.slack.api.group.groups.GroupsRenameMethod
import com.kreait.slack.api.group.groups.GroupsRepliesMethod
import com.kreait.slack.api.group.groups.GroupsSetPurposeMethod
import com.kreait.slack.api.group.groups.GroupsSetTopicMethod
import com.kreait.slack.api.group.groups.GroupsUnarchiveMethod

class DefaultGroupsMethodGroup : GroupsMethodGroup {
    override fun archive(authToken: String): GroupsArchiveMethod {
        return DefaultGroupsArchiveMethod(authToken)
    }

    override fun create(authToken: String): GroupsCreateMethod {
        return DefaultGroupsCreateMethod(authToken)
    }

    override fun createChild(authToken: String): GroupsCreateChildMethod {
        return DefaultGroupsCreateChildMethod(authToken)
    }

    override fun history(authToken: String): GroupsHistoryMethod {
        return DefaultGroupsHistoryMethod(authToken)
    }

    override fun info(authToken: String): GroupsInfoMethod {
        return DefaultGroupsInfoMethod(authToken)
    }

    override fun invite(authToken: String): GroupsInviteMethod {
        return DefaultGroupsInviteMethod(authToken)
    }

    override fun kick(authToken: String): GroupsKickMethod {
        return DefaultGroupsKickMethod(authToken)
    }

    override fun leave(authToken: String): GroupsLeaveMethod {
        return DefaultGroupsLeaveMethod(authToken)
    }

    override fun list(authToken: String): GroupsListMethod {
        return DefaultGroupsListMethod(authToken)
    }

    override fun mark(authToken: String): GroupsMarkMethod {
        return DefaultGroupsMarkMethod(authToken)
    }

    override fun open(authToken: String): GroupsOpenMethod {
        return DefaultGroupsOpenMethod(authToken)
    }

    override fun rename(authToken: String): GroupsRenameMethod {
        return DefaultGroupsRenameMethod(authToken)
    }

    override fun replies(authToken: String): GroupsRepliesMethod {
        return DefaultGroupsRepliesMethod(authToken)
    }

    override fun setPurpose(authToken: String): GroupsSetPurposeMethod {
        return DefaultGroupsSetPurposeMethod(authToken)
    }

    override fun setTopic(authToken: String): GroupsSetTopicMethod {
        return DefaultGroupsSetTopicMethod(authToken)
    }

    override fun unarchive(authToken: String): GroupsUnarchiveMethod {
        return DefaultGroupsUnarchiveMethod(authToken)
    }
}
