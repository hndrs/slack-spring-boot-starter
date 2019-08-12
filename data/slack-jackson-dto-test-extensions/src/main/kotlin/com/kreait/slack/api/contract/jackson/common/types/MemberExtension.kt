package com.kreait.slack.api.contract.jackson.common.types

import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.common.types.UserProfile
import com.kreait.slack.api.contract.jackson.common.types.sample

fun Member.Companion.sample() = Member("", "", "", false, "", "", "", "", 0, UserProfile.sample(),
        false, false, false, false, false, false, 0, false, false, "")
