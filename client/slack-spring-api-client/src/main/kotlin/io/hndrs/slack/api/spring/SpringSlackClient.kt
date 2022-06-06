package io.hndrs.slack.api.spring


import io.hndrs.slack.api.SlackClient
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
import io.hndrs.slack.api.spring.group.auth.SpringAuthMethodGroup
import io.hndrs.slack.api.spring.group.chat.SpringChatMethodGroup
import io.hndrs.slack.api.spring.group.conversations.SpringConversationsMethodGroup
import io.hndrs.slack.api.spring.group.dialog.SpringDialogMethodGroup
import io.hndrs.slack.api.spring.group.oauth.SpringOauthMethodGroup
import io.hndrs.slack.api.spring.group.pins.SpringPinsMethodGroup
import io.hndrs.slack.api.spring.group.reminders.SpringRemindersMethodGroup
import io.hndrs.slack.api.spring.group.respond.SpringRespondMethodGroup
import io.hndrs.slack.api.spring.group.team.SpringTeamMethodGroup
import io.hndrs.slack.api.spring.group.usergroups.SpringUsergroupMethodGroup
import io.hndrs.slack.api.spring.group.users.SpringUserMethodGroup

/**
 * Api Client to interact with the slack api
 */
class SpringSlackClient : SlackClient {

    /**
     * Convenience function to apply slack api oauth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun respond(): RespondMethodGroup {
        return SpringRespondMethodGroup()
    }

    /**
     * Convenience function to apply slack api auth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun auth(): AuthGroup {
        return SpringAuthMethodGroup()
    }

    /**
     * Convenience function to apply slack api chat method grouping
     *
     *  [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun chat(): ChatMethodGroup {
        return SpringChatMethodGroup()
    }

    /**
     * Convenience function to apply slack api dialog method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun dialog(): DialogMethodGroup {
        return SpringDialogMethodGroup()
    }

    /**
     * Convenience function to apply slack api conversation method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun conversation(): ConversationsMethodGroup {
        return SpringConversationsMethodGroup()
    }

    /**
     * Convenience function to apply slack api users method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun users(): UsersMethodGroup {
        return SpringUserMethodGroup()
    }

    /**
     * Convenience function to apply slack api oauth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun oauth(): OauthMethodGroup {
        return SpringOauthMethodGroup()
    }

    /**
     * Convenience function to apply slack api Team method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun team(): TeamMethodGroup {
        return SpringTeamMethodGroup()
    }

    /**
     * Convenience function to apply slack api Usergroups method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun usergroups(): UsergroupsMethodGroup {
        return SpringUsergroupMethodGroup()
    }

    /**
     * Convenience function to apply slack api Usergroups method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun reminders(): RemindersMethodGroup {
        return SpringRemindersMethodGroup()
    }

    /**
     * Convenience function to apply slack api Pins method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun pins(): PinsMethodGroup {
        return SpringPinsMethodGroup()
    }

    override fun apps() {
        TODO("Not yet implemented")
    }

    /**
     * [SpringSlackClient] configuration class that contains slack configuration options
     */
    data class Config constructor(val slackToken: String)
}
