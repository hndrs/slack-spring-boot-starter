package com.kreait.slack.api.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.TeamGetProfileRequest
import com.kreait.slack.api.group.ApiCallMethod

abstract class TeamGetProfileMethod : ApiCallMethod<TeamGetProfileMethod, SuccessfulTeamGetProfileResponse, ErrorTeamGetProfileResponse, TeamGetProfileRequest>()
