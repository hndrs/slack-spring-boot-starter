package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.data.event.EventController
import com.kreait.slack.broker.data.team.TeamController
import com.kreait.slack.broker.data.user.UserController
import com.kreait.slack.broker.store.event.EventStore
import com.kreait.slack.broker.store.team.TeamStore
import com.kreait.slack.broker.store.user.UserStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RestDataAutoConfiguration {

    /**
     * Registers the [TeamController]
     */
    @Bean
    open fun teamController(teamStore: TeamStore): TeamController {
        return TeamController(teamStore)
    }

    /**
     * Registers the [UserController]
     */
    @Bean
    open fun userController(userStore: UserStore): UserController {
        return UserController(userStore)
    }

    /**
     * Registers the [EventController]
     */
    @Bean
    open fun eventsController(eventStore: EventStore): EventController {
        return EventController(eventStore)
    }
}
