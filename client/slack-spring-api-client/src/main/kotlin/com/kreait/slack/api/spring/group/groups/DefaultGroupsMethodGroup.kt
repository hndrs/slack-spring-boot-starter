package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.group.groups.GroupsArchiveMethod
import com.kreait.slack.api.group.groups.GroupsMethodGroup

class DefaultGroupsMethodGroup : GroupsMethodGroup {
    override fun archive(authToken: String): GroupsArchiveMethod {
        return DefaultGroupsArchiveMethod(authToken)
    }

    override fun create(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createChild(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun history(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invite(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kick(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun leave(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mark(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun open(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rename(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTopic(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
