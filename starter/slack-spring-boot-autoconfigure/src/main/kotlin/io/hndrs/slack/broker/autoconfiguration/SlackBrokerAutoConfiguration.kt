package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.command.CommandBroker
import io.hndrs.slack.broker.command.CommandNotFoundReceiver
import io.hndrs.slack.broker.command.MismatchCommandReceiver
import io.hndrs.slack.broker.command.SlashCommandArgumentResolver
import io.hndrs.slack.broker.command.SlashCommandReceiver
import io.hndrs.slack.broker.event.EventReceiver
import io.hndrs.slack.broker.event.http.EventArgumentResolver
import io.hndrs.slack.broker.event.http.EventBroker
import io.hndrs.slack.broker.interactive.BlockActionReceiver
import io.hndrs.slack.broker.interactive.InteractiveComponentBroker
import io.hndrs.slack.broker.interactive.views.ViewClosedReceiver
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * AutoConfiguration for SlackBroker
 *
 * @property configuration The configuration properties with which you can customise the auto-configuration
 */
@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class SlackBrokerAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties) {

    /**
     * Configuration that registers the Broker-Components
     *
     * @property configuration The Configuration properties with which you can customise the auto-configuration
     * @property credentialsProvider CredentialsProvider-chain that retrieves the credentials
     */
    @Configuration
    open class BrokerAutoConfiguration(
        private val configuration: SlackBrokerConfigurationProperties,
        private val credentialsProvider: CredentialsProvider,
    ) : WebMvcConfigurer {

        /**
         * Registers the [InMemoryEventStore] when no other [EventStore] is registered
         */
        @ConditionalOnMissingBean
        @Bean
        open fun eventStore(): EventStore {
            return InMemoryEventStore()
        }

        /**
         * Registers the [EventBroker] which forwards events to all [EventReceiver]s
         */
        @Bean
        open fun eventBroker(
            slackEventReceivers: List<EventReceiver>,
            teamStore: TeamStore,
            eventStore: EventStore,
        ): EventBroker {
            return EventBroker(slackEventReceivers, teamStore, eventStore)
        }


        //TODO replace with seperate configuration
        @Bean
        open fun interactiveComponentBroker(
            blockActionReceivers: Set<BlockActionReceiver>,
            viewClosedReceivers: Set<ViewClosedReceiver>,
            teamStore: TeamStore,
        ): InteractiveComponentBroker {
            return InteractiveComponentBroker(blockActionReceivers, viewClosedReceivers, teamStore)
        }

        /**
         * Registers the [CommandBroker] which forwards commands to all [SlashCommandReceiver]s
         */
        @Bean
        open fun commandBroker(
            slackEventReceivers: List<SlashCommandReceiver>,
            teamStore: TeamStore,
            mismatchCommandReceiver: MismatchCommandReceiver?,
            slack: Slack,
        ): CommandBroker {
            return CommandBroker(slackEventReceivers, teamStore, slack, mismatchCommandReceiver)
        }

        /**
         * Adds argument-resolver to resolve incoming requests
         *
         * @param resolvers
         */
        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            val signingSecret = credentialsProvider.applicationCredentials().signingSecret

            resolvers.add(SlashCommandArgumentResolver(signingSecret))
            resolvers.add(EventArgumentResolver(signingSecret))
        }

        /**
         * Registers a [MismatchCommandReceiver] that responds to unknown commands
         */
        @ConditionalOnMissingBean
        @ConditionalOnProperty(
            prefix = SlackBrokerConfigurationProperties.MISMATCH_PROPERTY_PREFIX,
            name = ["enabled"],
            havingValue = "true",
            matchIfMissing = true
        )
        @Bean
        open fun commandNotFoundMismatchReceiver(slackClient: Slack): MismatchCommandReceiver {
            return CommandNotFoundReceiver(configuration.commands.mismatch.text)
        }
    }
}
