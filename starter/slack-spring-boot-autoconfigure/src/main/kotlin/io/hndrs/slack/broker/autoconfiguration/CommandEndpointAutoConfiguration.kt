package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.command.CommandEndpoint
import io.hndrs.slack.broker.command.CommandHandler
import io.hndrs.slack.broker.command.DefaultUnknownCommandHandler
import io.hndrs.slack.broker.command.SlashCommandArgumentResolver
import io.hndrs.slack.broker.command.UnknownCommandHandler
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableConfigurationProperties(CommandEndpointConfigurationProperties::class)
open class CommandEndpointAutoConfiguration(
    private val properties: CommandEndpointConfigurationProperties,
    private val credentialsProvider: CredentialsProvider,
) : WebMvcConfigurer {

    /**
     * Registers the [CommandEndpoint] which forwards commands to all [CommandHandler]s
     */
    @Bean
    open fun commandEndpoint(
        teamStore: TeamStore,
        slackEventReceivers: List<CommandHandler>,
        unknownCommandHandler: UnknownCommandHandler?,
    ): CommandEndpoint {
        return CommandEndpoint(slackEventReceivers, teamStore, unknownCommandHandler)
    }

    /**
     * Registers a [UnknownCommandHandler] that responds to unknown commands
     */
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
        prefix = CommandEndpointConfigurationProperties.UNKNOWN_COMMAND_PREFIX,
        name = ["enabled"],
        havingValue = "true",
        matchIfMissing = true
    )
    @Bean
    open fun defaultUnknownCommandHandler(slack: Slack): UnknownCommandHandler {
        return DefaultUnknownCommandHandler(slack, properties.unknownCommand.unknownCommandResponse)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(SlashCommandArgumentResolver(credentialsProvider.applicationCredentials().signingSecret))
    }
}
