package io.hndrs.slack.api.group.team

import io.hndrs.slack.api.contract.jackson.group.team.ErrorProfileResponse
import io.hndrs.slack.api.contract.jackson.group.team.ProfileRequest
import io.hndrs.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/team.profile.get
 */
abstract class TeamGetProfileMethod :
    ApiCallMethod<TeamGetProfileMethod, SuccessfulProfileResponse, ErrorProfileResponse, ProfileRequest>()
