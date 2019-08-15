package com.kreait.slack.api

import com.kreait.slack.api.group.auth.AuthGroup
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.dialog.DialogMethodGroup
import com.kreait.slack.api.group.im.ImMethodGroup
import com.kreait.slack.api.group.oauth.OauthMethodGroup
import com.kreait.slack.api.group.respond.RespondMethodGroup
import com.kreait.slack.api.group.team.TeamMethodGroup
import com.kreait.slack.api.group.usergroups.UsergroupsMethodGroup
import com.kreait.slack.api.group.users.UsersMethodGroup

interface SlackClient {

    /**
     * Convenience function to handle the authentication token
     *
     *  [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun auth(): AuthGroup

    /**
     * Convenience function to apply slack api chat method grouping
     *
     *  [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun chat(): ChatMethodGroup

    /**
     * Convenience function to apply slack api dialog method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun dialog(): DialogMethodGroup

    /**
     * Convenience function to apply slack api conversation method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun conversation(): ConversationsMethodGroup

    /**
     * Convenience function to apply slack api channel method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */

    fun channel(): ChannelsMethodGroup

    /**
     * Convenience function to apply slack api im method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun im(): ImMethodGroup

    fun users(): UsersMethodGroup

    /**
     * Convenience function to apply slack api oauth method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun oauth(): OauthMethodGroup

    /**
     * Convenience function to reply to slack interactions
     *
     * [Slack Api Documentation](https://api.slack.com/slash-commands#responding_response_url)
     */
    fun respond(): RespondMethodGroup

    /**
     * Convenience function to apply slack api Team method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun team(): TeamMethodGroup

    /**
     * Convenience function to apply slack api Usergroups method grouping
     *
     * [Slack Api Documentation](https://api.slack.com/methods)
     */
    fun usergroups(): UsergroupsMethodGroup

}
