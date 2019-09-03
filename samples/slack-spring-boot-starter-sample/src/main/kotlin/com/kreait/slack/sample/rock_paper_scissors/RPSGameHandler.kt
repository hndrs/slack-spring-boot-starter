package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.sample.rock_paper_scissors.data.Result
import com.kreait.slack.sample.rock_paper_scissors.data.WEAPONS
import org.springframework.stereotype.Component

@Component
class RPSGameHandler() {


    fun play(weapon: WEAPONS): Result {
        val against = listOf(WEAPONS.ROCK, WEAPONS.PAPER, WEAPONS.SCISSORS).random()
        return userWon(weapon, against)
    }

    private fun userWon(weapon: WEAPONS, against: WEAPONS): Result {
        if (weapon == against)
            return Result(against, userWon = false, draw = true)
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
}