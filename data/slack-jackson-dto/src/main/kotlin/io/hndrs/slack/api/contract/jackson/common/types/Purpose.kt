package io.hndrs.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.util.InstantToInt
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant


/**
 * The Purpose of a Channel describes its usage
 *
 * @property value the text of the purpose
 * @property createdBy user id of the purpose author
 * @property lastModifiedAt time when the purpose was modified
 */
@JacksonDataClass
data class Purpose(
    @JsonProperty("value") val value: String,
    @JsonProperty("creator") val createdBy: String,
    @InstantToInt @JsonProperty("last_set") val lastModifiedAt: Instant
) {

    companion object
}
