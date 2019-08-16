package com.kreait.slack.api.spring.group.groups

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DefaultGroupsMethodGroupTest() {
    /**
     * This test is used only for initial groups methodgroup setup, detailed tests will follow later
     */
    @Test
    fun test() {
        val defaultGroupsMethodGroup = DefaultGroupsMethodGroup()
        Assertions.assertTrue(defaultGroupsMethodGroup.archive("") is DefaultGroupsArchiveMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.create("") is DefaultGroupsCreateMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.createChild("") is DefaultGroupsCreateChildMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.history("") is DefaultGroupsHistoryMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.info("") is DefaultGroupsInfoMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.invite("") is DefaultGroupsInviteMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.kick("") is DefaultGroupsKickMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.leave("") is DefaultGroupsLeaveMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.list("") is DefaultGroupsListMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.mark("") is DefaultGroupsMarkMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.open("") is DefaultGroupsOpenMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.rename("") is DefaultGroupsRenameMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.replies("") is DefaultGroupsRepliesMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.setPurpose("") is DefaultGroupsSetPurposeMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.setTopic("") is DefaultGroupsSetTopicMethod)
        Assertions.assertTrue(defaultGroupsMethodGroup.unarchive("") is DefaultGroupsUnarchiveMethod)
    }
}