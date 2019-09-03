package com.kreait.slack.sample.rock_paper_scissors.data

enum class WEAPONS(val weaponName: String, val actionId: String) {
    ROCK("Rock", "ROCK_ACTION"),
    PAPER("Paper", "PAPER_ACTION"),
    SCISSORS("Scissors", "SCISSORS_ACTION")

}

class Result(val botWeapon: WEAPONS, val userWon: Boolean, val draw: Boolean = false)