package com.kreait.slack.broker.store.user

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.group.users.ListAllRequest
import com.kreait.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class UserManager @Autowired constructor(private val slackClient: SlackClient,
                                         private val userStore: UserStore) {

    companion object {
        val LOG = LoggerFactory.getLogger(UserManager::class.java)
        fun userOfMember(member: Member): User {
            return User(member.id, member.teamId, member.name, member.isDeleted, member.color, member.realName,
                    member.timezone, member.timezoneLabel, member.timezoneOffset,
                    User.UserProfile(member.profile.title, member.profile.phone, member.profile.skype,
                            member.profile.realName, member.profile.realNameNormalized, member.profile.displayName,
                            member.profile.displayNameNormalized, member.profile.fields, member.profile.statusText,
                            member.profile.statusEmoji, member.profile.statusExpiration, member.profile.avatarHash,
                            member.profile.alwaysActive, member.profile.imageOriginal, member.profile.email,
                            member.profile.firstName, member.profile.lastName, member.profile.image24, member.profile.image32,
                            member.profile.image48, member.profile.image72, member.profile.image192, member.profile.image512,
                            member.profile.image1024, member.profile.statusTextCanonical, member.profile.team),
                    member.isAdmin, member.isOwner, member.isPrimaryOwner, member.isRestricted, member.isUltraRestricted, member.isBot,
                    member.lastModifiedAt, member.isAppUser, member.has2fa, member.locale)
        }

        fun userOfLocalUser(localUser: FileUserStore.LocalUser): User {
            return User(localUser.id, localUser.teamId, localUser.name, localUser.isDeleted, localUser.color, localUser.realName,
                    localUser.timezone, localUser.timezoneLabel, localUser.timezoneOffset,
                    User.UserProfile(localUser.profile.title, localUser.profile.phone, localUser.profile.skype, localUser.profile.realName,
                            localUser.profile.realNameNormalized, localUser.profile.displayName, localUser.profile.displayNameNormalized,
                            localUser.profile.fields, localUser.profile.statusText, localUser.profile.statusEmoji,
                            localUser.profile.statusExpiration, localUser.profile.avatarHash, localUser.profile.alwaysActive,
                            localUser.profile.imageOriginal, localUser.profile.email, localUser.profile.firstName, localUser.profile.lastName,
                            localUser.profile.image24, localUser.profile.image32, localUser.profile.image48, localUser.profile.image72,
                            localUser.profile.image192, localUser.profile.image512, localUser.profile.image1024,
                            localUser.profile.statusTextCanonical, localUser.profile.team)
                    , localUser.isAdmin,
                    localUser.isOwner, localUser.isPrimaryOwner, localUser.isRestricted, localUser.isUltraRestricted, localUser.isBot,
                    localUser.lastModifiedAt, localUser.isAppUser, localUser.has2fa, localUser.locale)
        }
    }

    fun downloadUsers(team: Team) {
        this.slackClient.users().listAll(authToken = team.bot.accessToken)
                .with(ListAllRequest(true, 100))
                .onSuccess { response ->
                    this.userStore.put(*response.members.map { userOfMember(it) }.toTypedArray())
                    if (LOG.isDebugEnabled)
                        LOG.debug("successfully downloaded users")
                }
                .onFailure {
                    LOG.error("failure while trying to load users")
                }
                .invoke()
    }


}