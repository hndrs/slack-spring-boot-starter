package com.kreait.slack.api.spring


import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.groups.GroupsMethodGroup
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.respond.RespondMethodGroup
import com.kreait.slack.api.group.team.TeamMethodGroup
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.spring.group.auth.DefaultAuthMethodGroup
import com.kreait.slack.api.spring.group.channels.DefaultChannelMethodGroup
import com.kreait.slack.api.spring.group.chat.DefaultChatMethodGroup
import com.kreait.slack.api.spring.group.conversations.DefaultConversationsMethodGroup
import com.kreait.slack.api.spring.group.dialog.DefaultDialogMethodGroup
import com.kreait.slack.api.spring.group.groups.DefaultGroupsMethodGroup
import com.kreait.slack.api.spring.group.im.DefaultImMethodGroup
import com.kreait.slack.api.spring.group.oauth.DefaultOauthMethodGroup
import com.kreait.slack.api.spring.group.respond.DefaultRespondMethodGroup
import com.kreait.slack.api.spring.group.team.DefaultTeamMethodGroup
import com.kreait.slack.api.spring.group.usergroups.DefaultUsergroupMethodGroup
import com.kreait.slack.api.spring.group.users.DefaultUserMethodGroup

/**
 * Api Client to interact with the slack api
 */
class DefaultSlackClient : SlackClient {

    /**
     * Convenience function to apply slack api oauth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun respond(): RespondMethodGroup {
        return DefaultRespondMethodGroup()
    }

    /**
     * Convenience function to apply slack api auth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun auth(): AuthGroup {
        return DefaultAuthMethodGroup()
    }

    /**
     * Convenience function to apply slack api chat method grouping
     *
     *  [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun chat(): ChatMethodGroup {
        return DefaultChatMethodGroup()
    }

    /**
     * Convenience function to apply slack api dialog method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun dialog(): DefaultDialogMethodGroup {
        return DefaultDialogMethodGroup()
    }

    /**
     * Convenience function to apply slack api conversation method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun conversation(): ConversationsMethodGroup {
        return DefaultConversationsMethodGroup()
    }

    /**
     * Convenience function to apply slack api channel method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun channel(): ChannelsMethodGroup {
        return DefaultChannelMethodGroup()
    }

    /**
     * Convenience function to apply slack api im method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun im(): ImMethodGroup {
        return DefaultImMethodGroup()
    }

    /**
     * Convenience function to apply slack api users method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    override fun users(): UsersMethodGroup {
        return DefaultUserMethodGroup()
    }

    /**
     * Convenience function to apply slack api oauth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun oauth(): OauthMethodGroup {
        return DefaultOauthMethodGroup()
    }

    /**
     * Convenience function to apply slack api Team method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun team(): TeamMethodGroup {
        return DefaultTeamMethodGroup()
    }

    /**
     * Convenience function to apply slack api Usergroups method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun usergroups(): UsergroupsMethodGroup {
        return DefaultUsergroupMethodGroup()
    }


    /**
     * Convenience function to apply slack api Usergroups method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    override fun groups(): GroupsMethodGroup {
        return DefaultGroupsMethodGroup()
    }


    /**
     * [DefaultSlackClient] configuration class that contains slack configuration options
     */
    data class Config constructor(val slackToken: String)
}
