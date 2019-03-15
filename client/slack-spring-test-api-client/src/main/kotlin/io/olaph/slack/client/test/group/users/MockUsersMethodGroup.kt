package io.olaph.slack.client.test.group.users

import io.olaph.slack.client.group.users.UserConversationsMethod
import io.olaph.slack.client.group.users.UserListMethod
import io.olaph.slack.client.group.users.UsersInfoMethod
import io.olaph.slack.client.group.users.UsersMethodGroup

class MockUsersMethodGroup : UsersMethodGroup {
    override fun info(authToken: String): UsersInfoMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun list(authToken: String): UserListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun conversations(authToken: String): UserConversationsMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}