package io.olaph.slack.dto.jackson.common.types

fun Identity.User.Companion.sample() = Identity.User("", "", "", "", "", "", "", "")

fun Identity.Team.Companion.sample() = Identity.Team("", "")

fun Identity.Companion.sample() = Identity(Identity.User.sample(), Identity.Team.sample())