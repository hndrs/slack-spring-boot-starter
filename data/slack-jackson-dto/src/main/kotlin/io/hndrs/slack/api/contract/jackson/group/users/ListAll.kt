package io.hndrs.slack.api.contract.jackson.group.users

import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

data class SuccessfulListAllResponse(val members: List<Member>) {
    companion object
}

@JacksonDataClass
data class ErrorListAllResponse constructor(val error: String) {
    companion object
}

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/users.list
 */
data class ListAllRequest(val includeLocale: Boolean? = null, val packageSize: Int? = null) {

    companion object
}
