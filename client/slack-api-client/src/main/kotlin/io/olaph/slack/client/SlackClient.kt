package io.olaph.slack.client

import io.olaph.slack.client.group.auth.AuthGroup
import io.olaph.slack.client.group.channels.ChannelsMethodGroup
import io.olaph.slack.client.group.chat.ChatMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.dialog.DialogMethodGroup
import io.olaph.slack.client.group.im.ImMethodGroup
import io.olaph.slack.client.group.oauth.OauthMethodGroup
import io.olaph.slack.client.group.users.UsersMethodGroup

interface SlackClient {

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
}
