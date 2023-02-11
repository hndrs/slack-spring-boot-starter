package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.autoconfiguration.credentials.DefaultCredentialsProviderChain
import io.hndrs.slack.broker.exception.SlackExceptionHandler
import io.hndrs.slack.broker.receiver.SL4JLoggingHandler
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(BaseConfigurationProperties::class)
open class BaseAutoConfiguration {

    /**
     * Registers a default credentials-provider chain if no different one is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun slackCredentialsProvider(): CredentialsProvider {
        return DefaultCredentialsProviderChain()
    }

    /**
     * Registers a [Slack] if no different client is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun slackClient(): Slack {
        return Slack.getInstance()
    }

    /**
     * Registers the [SlackExceptionHandler]
     */
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    open fun slackExceptionHandler(): SlackExceptionHandler {
        return SlackExceptionHandler()
    }

    /**
     * Registers the [EvaluationReport]
     */
    @Bean
    open fun slackEvaluationReport(): EvaluationReport {
        return EvaluationReport()
    }

    /**
     * Registers a logging receiver that logs all incoming requests
     * Can be turned off by setting [SlackBrokerConfigurationProperties.LOGGING_PROPERTY_PREFIX].enabled to [false]
     * @return
     */
    @ConditionalOnProperty(
        prefix = LOGGING_PROPERTY_PREFIX,
        name = ["enabled"],
        havingValue = "true",
        matchIfMissing = true
    )
    @Bean
    open fun sL4JLoggingReceiver(): SL4JLoggingHandler {
        return SL4JLoggingHandler()
    }

    companion object {
        private const val PROPERTY_PREFIX = "slack"
        private const val LOGGING_PROPERTY_PREFIX = "$PROPERTY_PREFIX.logging"
    }
}
