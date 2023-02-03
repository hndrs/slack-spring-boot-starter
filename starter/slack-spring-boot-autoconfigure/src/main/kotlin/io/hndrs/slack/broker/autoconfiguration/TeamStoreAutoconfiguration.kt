package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.store.team.FileTeamStore
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * AutoConfiguration that registers a [TeamStore]
 */
@EnableConfigurationProperties(TeamStoreConfigurationProperties::class)
@Configuration
open class TeamStoreAutoconfiguration {

    /**
     * Registers the [InMemoryTeamStore] if no other is defined and the property
     * [SlackBrokerConfigurationProperties.TEAM_STORE].type is set to memory
     */
    @ConditionalOnProperty(
        prefix = TeamStoreConfigurationProperties.PREFIX,
        name = ["type"],
        havingValue = "memory",
        matchIfMissing = true
    )
    @ConditionalOnMissingBean
    @Bean
    open fun inMemoryTeamStore(): TeamStore {
        return InMemoryTeamStore()
    }

    /**
     * Registers the [FileTeamStore] if no other is defined and the property
     * [SlackBrokerConfigurationProperties.TEAM_STORE].type is set to file
     */
    @ConditionalOnProperty(
        prefix = TeamStoreConfigurationProperties.PREFIX,
        name = ["type"],
        havingValue = "file"
    )
    @ConditionalOnMissingBean
    @Bean
    open fun localTeamStore(): TeamStore {
        return FileTeamStore()
    }
}
