package io.hndrs.slack.broker.autoconfiguration.credentials

/**
 * Default implementation of a [CredentialsProviderChain]
 * Default-order:
 *  - [CredentialsFileCredentialsProvider]
 *  - [SystemPropertyCredentialsProvider]
 *  - [EnvironmentVariableCredentialsProvider]
 */
class DefaultCredentialsProviderChain
    : CredentialsProviderChain(
    listOf(
        CredentialsFileCredentialsProvider(),
        SystemPropertyCredentialsProvider(),
        EnvironmentVariableCredentialsProvider()
    )
)
