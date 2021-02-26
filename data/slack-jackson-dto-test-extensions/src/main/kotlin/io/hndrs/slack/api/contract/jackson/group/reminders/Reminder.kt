package io.hndrs.slack.api.contract.jackson.group.reminders

import io.hndrs.slack.api.contract.jackson.common.InstantSample
import io.hndrs.slack.api.contract.jackson.group.reminders.common.Reminder

fun Reminder.Companion.sample(): Reminder {
    return Reminder(InstantSample.sample(), "", "", false, "", InstantSample.sample(), "")
}
