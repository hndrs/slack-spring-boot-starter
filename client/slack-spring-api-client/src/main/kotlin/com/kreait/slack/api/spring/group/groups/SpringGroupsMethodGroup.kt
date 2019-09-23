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

class SpringGroupsMethodGroup : GroupsMethodGroup {
    override fun archive(authToken: String): GroupsArchiveMethod {
        return SpringGroupsArchiveMethod(authToken)
    }

    override fun create(authToken: String): GroupsCreateMethod {
        return SpringGroupsCreateMethod(authToken)
    }

    override fun createChild(authToken: String): GroupsCreateChildMethod {
        return SpringGroupsCreateChildMethod(authToken)
    }

    override fun history(authToken: String): GroupsHistoryMethod {
        return SpringGroupsHistoryMethod(authToken)
    }

    override fun info(authToken: String): GroupsInfoMethod {
        return SpringGroupsInfoMethod(authToken)
    }

    override fun invite(authToken: String): GroupsInviteMethod {
        return SpringGroupsInviteMethod(authToken)
    }

    override fun kick(authToken: String): GroupsKickMethod {
        return SpringGroupsKickMethod(authToken)
    }

    override fun leave(authToken: String): GroupsLeaveMethod {
        return SpringGroupsLeaveMethod(authToken)
    }

    override fun list(authToken: String): GroupsListMethod {
        return SpringGroupsListMethod(authToken)
    }

    override fun mark(authToken: String): GroupsMarkMethod {
        return SpringGroupsMarkMethod(authToken)
    }

    override fun open(authToken: String): GroupsOpenMethod {
        return SpringGroupsOpenMethod(authToken)
    }

    override fun rename(authToken: String): GroupsRenameMethod {
        return SpringGroupsRenameMethod(authToken)
    }

    override fun replies(authToken: String): GroupsRepliesMethod {
        return SpringGroupsRepliesMethod(authToken)
    }

    override fun setPurpose(authToken: String): GroupsSetPurposeMethod {
        return SpringGroupsSetPurposeMethod(authToken)
    }

    override fun setTopic(authToken: String): GroupsSetTopicMethod {
        return SpringGroupsSetTopicMethod(authToken)
    }

    override fun unarchive(authToken: String): GroupsUnarchiveMethod {
        return SpringGroupsUnarchiveMethod(authToken)
    }
}
