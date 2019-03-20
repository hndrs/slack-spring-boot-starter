package io.olaph.slack.client.group.channels

interface ChannelsMethodGroup {


    /**Archives a channel.*/
    fun archive(authToken:String): ChannelsArchiveMethod

    /**Creates a channel.*/
    fun create(authToken:String): ChannelsCreateMethod

    /**Fetches history of messages and events from a channel.*/
    fun history(authToken:String): ChannelsHistoryMethod

    /**Gets information about a channel.*/
    fun info(authToken:String): ChannelsInfoMethod

    /**Invites a user to a channel.*/
    fun invite(authToken:String): ChannelsInviteMethod

    /**Joins a channel, creating it if needed.*/
    fun join(authToken:String): ChannelsJoinMethod

    /**Removes a user from a channel.*/
    fun kick(authToken:String): ChannelsKickMethod

    /**Leaves a channel.*/
    fun leave(authToken:String): ChannelsLeaveMethod

    /**Lists all channels in a Slack team.*/
    fun list(authToken:String): ChannelsListMethod

    /**Sets the read cursor in a channel.*/
    fun mark(authToken:String): ChannelsMarkMethod

    /**Renames a channel.*/
    fun rename(authToken:String): ChannelsRenameMethod

    /**Retrieve a thread of messages posted to a channel*/
    fun replies(authToken:String): ChannelsRepliesMethod

    /**Sets the purpose for a channel.*/
    fun setPurpose(authToken:String): SetChannelsPurposeMethod

    /**Sets the topic for a channel.*/
    fun setTopic(authToken:String): SetChannelsTopicMethod

    /**Unarchives a channel.*/
    fun unarchive(authToken:String): ChannelsUnarchiveMethod
}
