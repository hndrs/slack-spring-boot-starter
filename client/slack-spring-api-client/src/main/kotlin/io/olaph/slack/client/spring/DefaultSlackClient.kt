package io.olaph.slack.client.spring


import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.group.auth.AuthGroup
import io.olaph.slack.client.group.channels.ChannelsMethodGroup
import io.olaph.slack.client.group.chat.ChatMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.im.ImMethodGroup
import io.olaph.slack.client.group.oauth.OauthMethodGroup
import io.olaph.slack.client.group.respond.RespondMethodGroup
import io.olaph.slack.client.group.users.UsersMethodGroup
import io.olaph.slack.client.spring.group.auth.DefaultAuthMethodGroup
import io.olaph.slack.client.spring.group.channels.DefaultChannelMethodGroup
import io.olaph.slack.client.spring.group.chat.DefaultChatMethodGroup
import io.olaph.slack.client.spring.group.conversations.DefaultConversationsMethodGroup
import io.olaph.slack.client.spring.group.dialog.DefaultDialogMethodGroup
import io.olaph.slack.client.spring.group.im.DefaultImMethodGroup
import io.olaph.slack.client.spring.group.oauth.DefaultOauthMethodGroup
import io.olaph.slack.client.spring.group.respond.DefaultRespondMethodGroup
import io.olaph.slack.client.spring.group.users.DefaultUserMethodGroup

/**
 * Api Client to interact with the slack api
 */
class DefaultSlackClient : SlackClient {
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
     * [DefaultSlackClient] configuration class that contains slack configuration options
     */
    data class Config constructor(val slackToken: String)
}
