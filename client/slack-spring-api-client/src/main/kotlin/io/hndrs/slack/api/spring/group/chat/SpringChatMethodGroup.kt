package io.hndrs.slack.api.spring.group.chat

import io.hndrs.slack.api.group.chat.ChatMethodGroup

/**
 * Convenience function to apply slack api chat method grouping
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringChatMethodGroup : ChatMethodGroup {

    override fun getPermalink(authToken: String): SpringGetPermalinkMethod {
        return SpringGetPermalinkMethod(authToken)
    }

    override fun meMessage(authToken: String): SpringMeMessageMethod {
        return SpringMeMessageMethod(authToken)
    }

    override fun unfurl(authToken: String): SpringUnfurlMethod {
        return SpringUnfurlMethod(authToken)
    }


    override fun delete(authToken: String): SpringDeleteMethod {
        return SpringDeleteMethod(authToken)
    }

    override fun postMessage(authToken: String): SpringPostMessageMethod {
        return SpringPostMessageMethod(authToken)
    }

    override fun postEphemeral(authToken: String): SpringPostEphemeralMethod {
        return SpringPostEphemeralMethod(authToken)
    }

    override fun update(authToken: String): SpringUpdateMethod {
        return SpringUpdateMethod(authToken)
    }
}
