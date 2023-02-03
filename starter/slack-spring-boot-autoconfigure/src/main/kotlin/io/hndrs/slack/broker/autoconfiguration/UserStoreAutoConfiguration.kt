package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.store.user.FileUserStore
import io.hndrs.slack.broker.store.user.InMemoryUserStore
import io.hndrs.slack.broker.store.user.UserInstallationReceiver
import io.hndrs.slack.broker.store.user.UserStore
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
@EnableConfigurationProperties(UserStoreConfigurationProperties::class)
@Configuration
open class UserStoreAutoConfiguration {

    /**
     * Registers the [InMemoryUserStore] if no other is defined and the property
     * [SlackBrokerConfigurationProperties.USER_STORE].type is set to memory
     */
    @ConditionalOnProperty(
        prefix = UserStoreConfigurationProperties.PREFIX,
        name = ["type"],
        havingValue = "memory"
    )
    @ConditionalOnMissingBean
    @Bean
    open fun userStore(): UserStore {
        return InMemoryUserStore()
    }

    /**
     * Registers the [FileUserStore] if no other is defined and the property
     * [SlackBrokerConfigurationProperties.USER_STORE].type is set to file
     */
    @ConditionalOnProperty(
        prefix = UserStoreConfigurationProperties.PREFIX,
        name = ["type"],
        havingValue = "file"
    )
    @ConditionalOnMissingBean
    @Bean
    open fun localUserStore(): UserStore {
        return FileUserStore()
    }

    /**
     * Registers the [UserInstallationReceiver] if a [UserStore]
     * registered, that downloads the user after an installation
     */
    @ConditionalOnBean(UserStore::class)
    @Bean
    open fun userInstallationReceiver(
        applicationContext: ApplicationContext,
        slackClient: Slack,
        userStore: UserStore,
    ): UserInstallationReceiver {
        return UserInstallationReceiver(slackClient, userStore)
    }
}
