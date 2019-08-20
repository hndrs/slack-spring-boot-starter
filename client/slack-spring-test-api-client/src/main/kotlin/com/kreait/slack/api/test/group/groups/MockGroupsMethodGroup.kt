package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.groups.GroupsOpenMethod
import com.kreait.slack.api.group.groups.GroupsRenameMethod
import com.kreait.slack.api.group.groups.GroupsRepliesMethod
import com.kreait.slack.api.group.groups.GroupsSetTopicMethod

class MockGroupsMethodGroup : GroupsMethodGroup {

    private val mockArchiveMethod = MockGroupsArchiveMethod()
    private val mockCreateMethod = MockGroupsCreateMethod()
    private val mockCreateChildMethod = MockGroupsCreateChildMethod()
    private val mockInviteMethod = MockGroupsInviteMethod()
    private val mockHistoryMethod = MockGroupsHistoryMethod()
    private val mockUnarchiveMethod = MockGroupsUnarchiveMethod()
    private val mockInfoMethod = MockGroupsInfoMethod()
    private val mockLeaveMethod = MockGroupsLeaveMethod()
    private val mockKickMethod = MockGroupsKickMethod()
    private val mockMarkMethod = MockGroupsMarkMethod()
    private val mockListMethod = MockGroupsListMethod()
    private val mockSetPurposeMethod = MockGroupsSetPurposeMethod()

    override fun archive(authToken: String): MockGroupsArchiveMethod {
        return mockArchiveMethod
    }

    override fun create(authToken: String): MockGroupsCreateMethod {
        return mockCreateMethod
    }

    override fun createChild(authToken: String): MockGroupsCreateChildMethod {
        return mockCreateChildMethod
    }

    override fun history(authToken: String): MockGroupsHistoryMethod {
        return mockHistoryMethod
    }

    override fun info(authToken: String): MockGroupsInfoMethod = mockInfoMethod

    override fun invite(authToken: String): MockGroupsInviteMethod = mockInviteMethod

    override fun kick(authToken: String): MockGroupsKickMethod = mockKickMethod

    override fun leave(authToken: String): MockGroupsLeaveMethod = mockLeaveMethod

    override fun list(authToken: String): MockGroupsListMethod = mockListMethod

    override fun mark(authToken: String): MockGroupsMarkMethod = mockMarkMethod

    override fun open(authToken: String): GroupsOpenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rename(authToken: String): GroupsRenameMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): GroupsRepliesMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String): MockGroupsSetPurposeMethod = mockSetPurposeMethod

    override fun setTopic(authToken: String): GroupsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): MockGroupsUnarchiveMethod {
        return mockUnarchiveMethod
    }


}
