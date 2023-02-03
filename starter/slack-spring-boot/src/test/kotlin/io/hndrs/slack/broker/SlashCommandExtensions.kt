package io.hndrs.slack.broker

import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.command.SlashCommand.Companion.API_APP_ID_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.CHANNEL_ID_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.CHANNEL_NAME_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.COMMAND_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.RESPONSE_URL_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.TEAM_DOMAIN_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.TEAM_ID_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.TEXT_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.TOKEN_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.TRIGGER_ID_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.USER_ID_PROPERTY_NAME
import io.hndrs.slack.broker.command.SlashCommand.Companion.USER_NAME_PROPERTY_NAME

fun SlashCommand.Companion.sample(): SlashCommand {
    return SlashCommand(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        null,
        null,
        ""
    )
}

fun SlashCommand.toParameterMap(): Map<String, List<String>> {
    val parameterMap = mutableMapOf<String, List<String>>()
    parameterMap[TOKEN_PROPERTY_NAME] = listOf(token)
    parameterMap[TEAM_ID_PROPERTY_NAME] = listOf(teamId)
    parameterMap[TEAM_DOMAIN_PROPERTY_NAME] = listOf(teamDomain)
    parameterMap[CHANNEL_ID_PROPERTY_NAME] = listOf(channelId)
    parameterMap[CHANNEL_NAME_PROPERTY_NAME] = listOf(channelName)
    parameterMap[USER_ID_PROPERTY_NAME] = listOf(userId)
    parameterMap[USER_NAME_PROPERTY_NAME] = listOf(userName)
    parameterMap[COMMAND_PROPERTY_NAME] = listOf(command)
    parameterMap[TEXT_PROPERTY_NAME] = listOf(text)
    parameterMap[RESPONSE_URL_PROPERTY_NAME] = listOf(responseUrl)
    parameterMap[TRIGGER_ID_PROPERTY_NAME] = listOf(triggerId)
    parameterMap[API_APP_ID_PROPERTY_NAME] = listOf(apiAppId)
    enterpriseId?.let { parameterMap[TRIGGER_ID_PROPERTY_NAME] = listOf(it) }
    enterpriseName?.let { listOf(it).also { parameterMap[TRIGGER_ID_PROPERTY_NAME] = it } }
    return parameterMap
}
