package io.hndrs.slack.api

import io.hndrs.slack.api.group.auth.AuthGroup
import io.hndrs.slack.api.group.chat.ChatMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.dialog.DialogMethodGroup
import io.hndrs.slack.api.group.oauth.OauthMethodGroup
import io.hndrs.slack.api.group.pins.PinsMethodGroup
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup
import io.hndrs.slack.api.group.respond.RespondMethodGroup
import io.hndrs.slack.api.group.team.TeamMethodGroup
import io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup
import io.hndrs.slack.api.group.users.UsersMethodGroup

/**
 * Slack client specification to interact with the slack api
 */
@Suppress("ComplexInterface")
interface SlackClient {

    /**
     * Convenience function to handle the authentication token
     *
     *  [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun auth(): io.hndrs.slack.api.group.auth.AuthGroup

    /**
     * Convenience function to apply slack api chat method grouping
     *
     *  [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun chat(): io.hndrs.slack.api.group.chat.ChatMethodGroup

    /**
     * Convenience function to apply slack api dialog method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun dialog(): io.hndrs.slack.api.group.dialog.DialogMethodGroup

    /**
     * Convenience function to apply slack api conversation method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun conversation(): io.hndrs.slack.api.group.conversations.ConversationsMethodGroup

    /**
     * Convenience function to apply slack api Users method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun users(): io.hndrs.slack.api.group.users.UsersMethodGroup

    /**
     * Convenience function to apply slack api oauth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun oauth(): io.hndrs.slack.api.group.oauth.OauthMethodGroup

    /**
     * Convenience function to reply to slack interactions
     *
     * [Slack Api Documentation](https://api.slack.com/slash-commands#responding_response_url)
     */
    fun respond(): io.hndrs.slack.api.group.respond.RespondMethodGroup

    /**
     * Convenience function to apply slack api Team method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun team(): io.hndrs.slack.api.group.team.TeamMethodGroup

    /**
     * Convenience function to apply slack api Usergroups method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun usergroups(): io.hndrs.slack.api.group.usergroups.UsergroupsMethodGroup

    /**
     * Convenience function to apply slack api reminders method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun reminders(): io.hndrs.slack.api.group.reminders.RemindersMethodGroup

    /**
     * Convenience function to apply slack api pins method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun pins(): io.hndrs.slack.api.group.pins.PinsMethodGroup

}
