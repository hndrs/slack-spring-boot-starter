package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.api.SlackClient
import com.kreait.slack.broker.store.user.FileUserStore
import com.kreait.slack.broker.store.user.InMemoryUserStore
import com.kreait.slack.broker.store.user.UserChangedEventReceiver
import com.kreait.slack.broker.store.user.UserInstallationReceiver
import com.kreait.slack.broker.store.user.UserJoinedEventReceiver
import com.kreait.slack.broker.store.user.UserStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * AutoConfiguration that registers a [UserStore]
 */
@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class UserStoreAutoConfiguration {

    /**
     * Registers the [InMemoryUserStore] if no other is defined and the property [SlackBrokerConfigurationProperties.USER_STORE].type is set to memory
     */
    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.USER_STORE, name = ["type"], havingValue = "memory", matchIfMissing = true)
    @ConditionalOnMissingBean
    @Bean
    open fun userStore(): UserStore {
        return InMemoryUserStore()
    }

    /**
     * Registers the [FileUserStore] if no other is defined and the property [SlackBrokerConfigurationProperties.USER_STORE].type is set to file
     */
    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.USER_STORE, name = ["type"], havingValue = "file")
    @ConditionalOnMissingBean
    @Bean
    open fun localUserStore(): UserStore {
        return FileUserStore()
    }

    /**
     * Registers the [UserInstallationReceiver] if a [UserStore] registered, that downloads the user after an installation
     */
    @ConditionalOnBean(UserStore::class)
    @Bean
    open fun userInstallationReceiver(applicationContext: ApplicationContext, slackClient: SlackClient, userStore: UserStore): UserInstallationReceiver? {
        return UserInstallationReceiver(slackClient, userStore)
    }

    /**
     * Registers the [UserJoinedEventReceiver] if a [UserStore] registered, which adds users to the [UserStore] when they join a team
     */
    @ConditionalOnBean(UserStore::class)
    @Bean
    open fun userJoinedReceiver(userStore: UserStore): UserJoinedEventReceiver {
        return UserJoinedEventReceiver(userStore)
    }

    /**
     * Registers the [UserChangedEventReceiver] if a [UserStore] registered, which modifies users in the [UserStore] modify their profile
     */
    @ConditionalOnBean(UserStore::class)
    @Bean
    open fun userChangedReceiver(userStore: UserStore): UserChangedEventReceiver {
        return UserChangedEventReceiver(userStore)
    }
}
