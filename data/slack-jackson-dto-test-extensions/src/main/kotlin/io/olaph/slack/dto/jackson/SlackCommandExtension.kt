package io.olaph.slack.dto.jackson

import io.olaph.slack.dto.jackson.SlackCommand.Companion.CHANNEL_ID_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.CHANNEL_NAME_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.COMMAND_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.RESPONSE_URL_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.TEAM_DOMAIN_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.TEAM_ID_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.TEXT_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.TOKEN_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.TRIGGER_ID_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.USER_ID_PROPERTY_NAME
import io.olaph.slack.dto.jackson.SlackCommand.Companion.USER_NAME_PROPERTY_NAME

fun SlackCommand.Companion.sample(): SlackCommand {
    return SlackCommand(
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
            null
    )
}

fun SlackCommand.toParameterMap(): Map<String, Array<out String>> {
    val parameterMap = mutableMapOf<String, Array<out String>>()

    parameterMap[TOKEN_PROPERTY_NAME] = arrayOf(token)
    parameterMap[TEAM_ID_PROPERTY_NAME] = arrayOf(teamId)
    parameterMap[TEAM_DOMAIN_PROPERTY_NAME] = arrayOf(teamDomain)
    parameterMap[CHANNEL_ID_PROPERTY_NAME] = arrayOf(channelId)
    parameterMap[CHANNEL_NAME_PROPERTY_NAME] = arrayOf(channelName)
    parameterMap[USER_ID_PROPERTY_NAME] = arrayOf(userId)
    parameterMap[USER_NAME_PROPERTY_NAME] = arrayOf(userName)
    parameterMap[COMMAND_PROPERTY_NAME] = arrayOf(command)
    parameterMap[TEXT_PROPERTY_NAME] = arrayOf(text)
    parameterMap[RESPONSE_URL_PROPERTY_NAME] = arrayOf(responseUrl)
    parameterMap[TRIGGER_ID_PROPERTY_NAME] = arrayOf(triggerId)

    enterpriseId?.let { parameterMap[TRIGGER_ID_PROPERTY_NAME] = arrayOf(it) }
    enterpriseName?.let { parameterMap[TRIGGER_ID_PROPERTY_NAME] = arrayOf(it) }

    return parameterMap
}
