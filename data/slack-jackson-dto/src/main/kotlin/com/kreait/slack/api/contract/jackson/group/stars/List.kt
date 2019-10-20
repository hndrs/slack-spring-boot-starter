package com.kreait.slack.api.contract.jackson.group.stars

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.File
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulStarsListResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorStarsListResponse::class, name = "false")
)

@JacksonDataClass
sealed class StarsListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulStarsListResponse(
    override val ok: Boolean,
    @JsonProperty("items") val items: List<StarsItem>,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : StarsListResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorStarsListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : StarsListResponse(ok) {
    companion object
}

@JacksonDataClass
data class StarsItem(
    @JsonProperty("type") val type: Type,
    @JsonProperty("channel") val channel: String? = null,
    @JsonProperty("group") val group: String? = null,
    @JsonProperty("message") val message: Message? = null,
    @JsonProperty("file") val file: File? = null
) {
    companion object

    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(
        @get:JsonValue val typeString: String
    ) {
        MESSAGE("message"),
        FILE("file"),
        FILE_COMMENT("file_comment"),
        CHANNEL("channel"),
        IM("im"),
        GROUP("group");

        class Serializer : JsonSerializer<Type>() {
            override fun serialize(value: Type, gen: JsonGenerator?, serializers: SerializerProvider?) {
                gen?.writeString(value.typeString.toLowerCase())
            }
        }

        class Deserializer : JsonDeserializer<Type>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Type {
                return valueOf(p.text.toUpperCase())
            }
        }

    }
}

/**
 * This method lists the items starred by the authed user.
 *
 * @property count Number of items to return per page.
 * @property cursor Parameter for pagination. Set cursor equal to the next_cursor attribute returned by the previous request's response_metadata.
 * @property limit The maximum number of items to return.
 * @property page Page number of results to return.
 */
data class StarsListRequest(
    @JsonProperty("count") val count: Int = 100,
    @JsonProperty("cursor") val cursor: String? = null,
    @JsonProperty("limit") val limit: Int = 0,
    @JsonProperty("page") val page: Int = 1
) {
    companion object
}
