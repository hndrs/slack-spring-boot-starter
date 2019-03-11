package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InstallationBroker
import io.olaph.slack.broker.broker.InteractiveComponentBroker
import io.olaph.slack.broker.configuration.InteractiveResponseArgumentResolver
import io.olaph.slack.broker.configuration.SlackCommandArgumentResolver
import io.olaph.slack.broker.exception.SlackExceptionHandler
import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class SlackBrokerAutoConfiguration constructor(private val configuration: SlackBrokerConfigurationProperties) {

    @Bean
    open fun eventReceiver(slackEventReceivers: List<EventReceiver>): EventBroker {
        return EventBroker(slackEventReceivers)
    }

    @Bean
    open fun commandReceiver(slackEventReceivers: List<SlashCommandReceiver>): CommandBroker {
        return CommandBroker(slackEventReceivers)
    }

    @Bean
    open fun componentReceiver(slackEventReceivers: List<InteractiveComponentReceiver>): InteractiveComponentBroker {
        return InteractiveComponentBroker(slackEventReceivers)
    }

    @Bean
    open fun installationBroker(installationReceivers: List<InstallationReceiver>): InstallationBroker {
        val installation = configuration.installation
        return InstallationBroker(installation.successRedirectUrl, installation.errorRedirectUrl, installationReceivers)
    }

    @Bean
    open fun slackExceptionHandler(): SlackExceptionHandler {
        return SlackExceptionHandler()
    }


    @Bean
    open fun sl4jLoggingBroker(): SL4JLoggingReceiver {
        return SL4JLoggingReceiver()
    }

    @Configuration
    open class SlackCommandHttpConverterConfiguration : WebMvcConfigurer {
        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            resolvers.add(SlackCommandArgumentResolver())
            resolvers.add(InteractiveResponseArgumentResolver())
        }
    }

}
