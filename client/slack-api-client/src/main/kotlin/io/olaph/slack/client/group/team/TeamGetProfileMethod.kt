package io.olaph.slack.client.group.team

import io.olaph.slack.client.group.ApiCallMethod
import io.olaph.slack.dto.jackson.group.team.ErrorTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.SuccessfulTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.TeamGetProfileRequest

abstract class TeamGetProfileMethod : ApiCallMethod<TeamGetProfileMethod, SuccessfulTeamGetProfileResponse, ErrorTeamGetProfileResponse, TeamGetProfileRequest>()