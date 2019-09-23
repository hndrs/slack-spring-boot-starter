package com.kreait.slack.api.spring


import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.group.respond.RespondMethodGroup
import com.kreait.slack.api.group.team.TeamMethodGroup
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.spring.group.auth.SpringAuthMethodGroup
import com.kreait.slack.api.spring.group.channels.SpringChannelsMethodGroup
import com.kreait.slack.api.spring.group.chat.SpringChatMethodGroup
import com.kreait.slack.api.spring.group.conversations.SpringConversationsMethodGroup
import com.kreait.slack.api.spring.group.dialog.SpringDialogMethodGroup
import com.kreait.slack.api.spring.group.groups.SpringGroupsMethodGroup
import com.kreait.slack.api.spring.group.im.SpringImMethodGroup
import com.kreait.slack.api.spring.group.oauth.SpringOauthMethodGroup
import com.kreait.slack.api.spring.group.respond.SpringRespondMethodGroup
import com.kreait.slack.api.spring.group.team.SpringTeamMethodGroup
import com.kreait.slack.api.spring.group.usergroups.SpringUsergroupMethodGroup
import com.kreait.slack.api.spring.group.users.SpringUserMethodGroup
import com.kreait.slack.api.spring.group.reminders.SpringRemindersMethodGroup

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
    override fun dialog(): SpringDialogMethodGroup {
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
     * Convenience function to apply slack api channel method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun channel(): ChannelsMethodGroup {
        return SpringChannelsMethodGroup()
    }

    /**
     * Convenience function to apply slack api im method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun im(): ImMethodGroup {
        return SpringImMethodGroup()
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
    override fun groups(): GroupsMethodGroup {
        return SpringGroupsMethodGroup()
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
     * [SpringSlackClient] configuration class that contains slack configuration options
     */
    data class Config constructor(val slackToken: String)
}
