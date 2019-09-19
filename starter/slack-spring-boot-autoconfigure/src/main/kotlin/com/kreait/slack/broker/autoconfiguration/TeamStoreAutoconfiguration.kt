package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.store.team.FileTeamStore
import com.kreait.slack.broker.store.team.InMemoryTeamStore
import com.kreait.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class TeamStoreAutoconfiguration {

    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.TEAM_STORE, name = ["type"], havingValue = "memory", matchIfMissing = true)
    @ConditionalOnMissingBean
    @Bean
    open fun teamStore(): TeamStore {
        return InMemoryTeamStore()
    }

    @ConditionalOnProperty(prefix = SlackBrokerConfigurationProperties.TEAM_STORE, name = ["type"], havingValue = "file")
    @ConditionalOnMissingBean
    @Bean
    open fun localTeamStore(): TeamStore {
        return FileTeamStore()
    }

}