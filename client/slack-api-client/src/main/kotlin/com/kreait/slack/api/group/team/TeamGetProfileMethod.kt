package com.kreait.slack.api.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.ProfileRequest
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/team.profile.get
 */
abstract class TeamGetProfileMethod : ApiCallMethod<TeamGetProfileMethod, SuccessfulProfileResponse, ErrorProfileResponse, ProfileRequest>()
