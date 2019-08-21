package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.group.groups.GroupsMethodGroup

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
    private val mockRenameMethod = MockGroupsRenameMethod()
    private val mockSetTopicMethod = MockGroupsSetTopicMethod()
    private val mockOpenMethod = MockGroupsOpenMethod()
    private val mockRepliesMethod = MockGroupsRepliesMethod()

    override fun archive(authToken: String): MockGroupsArchiveMethod = mockArchiveMethod

    override fun create(authToken: String): MockGroupsCreateMethod = mockCreateMethod

    override fun createChild(authToken: String): MockGroupsCreateChildMethod = mockCreateChildMethod

    override fun history(authToken: String): MockGroupsHistoryMethod = mockHistoryMethod

    override fun info(authToken: String): MockGroupsInfoMethod = mockInfoMethod

    override fun invite(authToken: String): MockGroupsInviteMethod = mockInviteMethod

    override fun kick(authToken: String): MockGroupsKickMethod = mockKickMethod

    override fun leave(authToken: String): MockGroupsLeaveMethod = mockLeaveMethod

    override fun list(authToken: String): MockGroupsListMethod = mockListMethod

    override fun mark(authToken: String): MockGroupsMarkMethod = mockMarkMethod

    override fun open(authToken: String): MockGroupsOpenMethod = mockOpenMethod

    override fun rename(authToken: String): MockGroupsRenameMethod = mockRenameMethod

    override fun replies(authToken: String): MockGroupsRepliesMethod = mockRepliesMethod

    override fun setPurpose(authToken: String): MockGroupsSetPurposeMethod = mockSetPurposeMethod

    override fun setTopic(authToken: String): MockGroupsSetTopicMethod = mockSetTopicMethod

    override fun unarchive(authToken: String): MockGroupsUnarchiveMethod = mockUnarchiveMethod


}
