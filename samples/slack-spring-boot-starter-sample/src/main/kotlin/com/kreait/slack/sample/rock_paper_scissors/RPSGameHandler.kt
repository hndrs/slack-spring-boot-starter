package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.BlockActions
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.common.messaging.Element
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.ResponseType
import com.kreait.slack.broker.store.team.Team
import com.kreait.slack.sample.rock_paper_scissors.data.Result
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RPSGameHandler @Autowired constructor(private val slackClient: SlackClient) {

    fun play(weapon: WEAPONS): Result {
        val against = listOf(WEAPONS.ROCK, WEAPONS.PAPER, WEAPONS.SCISSORS).random()
        return userWon(weapon, against)
    }

    private fun userWon(weapon: WEAPONS, against: WEAPONS): Result {
        if (weapon == against) {
            return Result(against, userWon = false, draw = true)
        }

        return when (weapon) {
            WEAPONS.ROCK -> { // Rock beats scissors
                Result(against, against == WEAPONS.SCISSORS)
            }
            WEAPONS.PAPER -> {// paper beats rock
                Result(against, against == WEAPONS.ROCK)

            }
            WEAPONS.SCISSORS -> { // scissors beats paper
                Result(against, against == WEAPONS.PAPER)
            }
        }
    }

    fun selectionHandler(interactiveComponentResponse: BlockActions) {
        val selection = (interactiveComponentResponse.actions?.first() as Element.Button).text.text
        selection.let { userSelection ->

            val gameResult = play(WEAPONS.valueOf(userSelection.toUpperCase()))

            if (gameResult.userWon) {
                this.slackClient.respond().message(interactiveComponentResponse.responseUrl!!)
                    .with(
                        RespondMessageRequest(
                            text = "I choose *${gameResult.botWeapon}*\n$selection beats ${gameResult.botWeapon}\n" +
                                    "*you won* this time :tada: :white_check_mark:",
                            responseType = ResponseType.EPHEMERAL
                        )
                    )
                    .onSuccess { println(it) }
                    .onFailure { println(it) }
                    .invoke()
            } else {
                if (gameResult.draw) {
                    this.slackClient.respond().message(interactiveComponentResponse.responseUrl!!)
                        .with(
                            RespondMessageRequest(
                                text = "I choose *${gameResult.botWeapon}*,\n this one is a *tie*, try again",
                                responseType = ResponseType.EPHEMERAL
                            )
                        )
                        .onSuccess { println(it) }
                        .onFailure { println(it) }
                        .invoke()
                } else {
                    this.slackClient.respond().message(interactiveComponentResponse.responseUrl!!)
                        .with(
                            RespondMessageRequest(
                                text = "I choose *${gameResult.botWeapon}*,\n ${gameResult.botWeapon}" +
                                        " beats $selection,\n*You lost* :x: ",
                                responseType = ResponseType.EPHEMERAL
                            )
                        )
                        .onSuccess { println(it) }
                        .onFailure { println(it) }
                        .invoke()
                }
            }
        }
    }

    fun dmHandler(weapon: WEAPONS, team: Team, slackEvent: SlackEvent) {
        val channelId = slackEvent.event["channel"] as String
        val result = play(weapon)

        when {
            result.userWon -> this.slackClient.chat().postMessage(team.bot.accessToken).with(
                PostMessageRequest(
                    text = "I choose ${result.botWeapon}.\n$weapon beats ${result.botWeapon}!\n*You won!* :trophy:",
                    channel = channelId
                )
            )
                .onSuccess { println(it) }
                .onFailure { println(it) }
                .invoke()
            result.draw -> this.slackClient.chat().postMessage(team.bot.accessToken).with(
                PostMessageRequest(
                    text = "I choose ${result.botWeapon}.\n*It's a tie!* :necktie:",
                    channel = channelId
                )
            )
                .onSuccess { println(it) }
                .onFailure { println(it) }
                .invoke()
            else -> this.slackClient.chat().postMessage(team.bot.accessToken).with(
                PostMessageRequest(
                    text = "I choose ${result.botWeapon}.\n$weapon beats ${result.botWeapon}!\n*You lost!* :poop:",
                    channel = channelId
                )
            )
                .onSuccess { println(it) }
                .onFailure { println(it) }
                .invoke()
        }
    }

    companion object {
        val blocks = listOf(
            Block.Section(text = Text(Text.Type.PLAIN_TEXT, "choose your weapon"), blockId = "weapons_block"),
            Block.Action(
                blockId = RockPaperScissorsCommandReceiver.RPS_BLOCK_ID,
                elements = listOf(
                    Element.Button(
                        text = Text(Text.Type.PLAIN_TEXT, WEAPONS.ROCK.weaponName),
                        actionId = WEAPONS.ROCK.actionId
                    ),
                    Element.Button(
                        text = Text(Text.Type.PLAIN_TEXT, WEAPONS.PAPER.weaponName),
                        actionId = WEAPONS.PAPER.actionId
                    ),
                    Element.Button(
                        text = Text(Text.Type.PLAIN_TEXT, WEAPONS.SCISSORS.weaponName),
                        actionId = WEAPONS.SCISSORS.actionId
                    )
                )
            )
        )
    }
}
