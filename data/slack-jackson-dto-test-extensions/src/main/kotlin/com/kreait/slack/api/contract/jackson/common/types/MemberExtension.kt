package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun Member.Companion.sample() = Member("", "", "", false, "", "", "", "", 0, UserProfile.sample(),
        isAdmin = false, isOwner = false, isPrimaryOwner = false, isRestricted = false, isUltraRestricted = false, isBot = false, lastModifiedAt = InstantSample.sample(), isAppUser = false, has2fa = false, locale = "")
