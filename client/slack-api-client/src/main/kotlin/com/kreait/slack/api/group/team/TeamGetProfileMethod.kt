package com.kreait.slack.api.group.team

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.team.ErrorTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.TeamGetProfileRequest

abstract class TeamGetProfileMethod : ApiCallMethod<TeamGetProfileMethod, SuccessfulTeamGetProfileResponse, ErrorTeamGetProfileResponse, TeamGetProfileRequest>()
