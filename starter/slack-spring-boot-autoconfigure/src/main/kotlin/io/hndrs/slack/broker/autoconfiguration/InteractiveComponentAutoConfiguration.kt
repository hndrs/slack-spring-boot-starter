package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.interactive.BlockActionReceiver
import io.hndrs.slack.broker.interactive.InteractiveComponentBroker
import io.hndrs.slack.broker.interactive.views.ViewClosedReceiver
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class InteractiveComponentAutoConfiguration {

    @Bean
    open fun interactiveComponentBroker(
        blockActionReceivers: Set<BlockActionReceiver>,
        viewClosedReceivers: Set<ViewClosedReceiver>,
        teamStore: TeamStore,
    ): InteractiveComponentBroker {
        return InteractiveComponentBroker(blockActionReceivers, viewClosedReceivers, teamStore)
    }
}
