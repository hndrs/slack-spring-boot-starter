package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.store.team.FileTeamStore
import com.kreait.slack.broker.store.team.InMemoryTeamStore
import com.kreait.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * AutoConfiguration that registers a [TeamStore]
 */
@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class TeamStoreAutoconfiguration {

    /**
     * Registers the [InMemoryTeamStore] if no other is defined and the property [SlackBrokerConfigurationProperties.TEAM_STORE].type is set to memory
     */
    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.TEAM_STORE, name = ["type"], havingValue = "memory", matchIfMissing = true)
    @ConditionalOnMissingBean
    @Bean
    open fun inMemoryTeamStore(): TeamStore {
        return InMemoryTeamStore()
    }

    /**
     * Registers the [FileTeamStore] if no other is defined and the property [SlackBrokerConfigurationProperties.TEAM_STORE].type is set to file
     */
    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.TEAM_STORE, name = ["type"], havingValue = "file")
    @ConditionalOnMissingBean
    @Bean
    open fun localTeamStore(): TeamStore {
        return FileTeamStore()
    }
}
