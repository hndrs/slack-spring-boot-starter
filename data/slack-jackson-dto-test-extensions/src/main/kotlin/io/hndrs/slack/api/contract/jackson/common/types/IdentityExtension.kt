package io.hndrs.slack.api.contract.jackson.common.types

fun Identity.User.Companion.sample() = Identity.User("", "", "", "", "", "", "", "")

fun Team.Companion.sample() = Team("", "")

fun Identity.Companion.sample() = Identity(Identity.User.sample(), Team.sample())
