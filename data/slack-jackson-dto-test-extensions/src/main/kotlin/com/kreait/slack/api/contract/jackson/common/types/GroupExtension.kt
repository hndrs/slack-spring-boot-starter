package com.kreait.slack.api.contract.jackson.common.types

fun Group.Companion.sample(): Group {
    return Group(0, "", "", false, true, false, "", "",
            listOf(), "", Purpose.sample(), Topic.sample(), 0, 0)
}

fun Purpose.Companion.sample(): Purpose {
    return Purpose("", 0, "")
}

fun Topic.Companion.sample(): Topic {
    return Topic("", 0, "")
}