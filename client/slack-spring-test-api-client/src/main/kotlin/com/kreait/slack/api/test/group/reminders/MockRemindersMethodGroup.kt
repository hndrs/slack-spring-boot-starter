package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.group.reminders.RemindersAddMethod
import com.kreait.slack.api.group.reminders.RemindersCompleteMethod
import com.kreait.slack.api.group.reminders.RemindersDeleteMethod
import com.kreait.slack.api.group.reminders.RemindersInfoMethod
import com.kreait.slack.api.group.reminders.RemindersListMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup


class MockRemindersMethodGroup : RemindersMethodGroup {
    override fun list(authToken: String): RemindersListMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String): RemindersInfoMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun complete(authToken: String): RemindersCompleteMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(authToken: String): RemindersAddMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(authToken: String): RemindersDeleteMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}