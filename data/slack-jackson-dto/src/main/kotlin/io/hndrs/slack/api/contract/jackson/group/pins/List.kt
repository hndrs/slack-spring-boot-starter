package io.hndrs.slack.api.contract.jackson.group.pins

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
import io.hndrs.slack.api.contract.jackson.common.types.File
import io.hndrs.slack.api.contract.jackson.common.types.Message
import io.hndrs.slack.api.contract.jackson.util.InstantToString
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulPinsListResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorPinsListResponse::class, name = "false")
)

@JacksonDataClass
sealed class PinsListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulPinsListResponse(
    override val ok: Boolean,
    @JsonProperty("items") val items: List<PinsItem>
) : PinsListResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
data class ErrorPinsListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : PinsListResponse(ok) {
    companion object
}

@JacksonDataClass
data class PinsItem(
    @JsonProperty("channel") val channel: String?,
    @InstantToString @JsonProperty("created") val created: Instant?,
    @JsonProperty("created_by") val createdBy: String?,
    @JsonProperty("message") val message: Message? = null,
    @JsonProperty("file") val file: File? = null,
    @JsonProperty("type") val type: Type
) {
    companion object

    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(
        @get:JsonValue val typeString: String
    ) {
        MESSAGE("message"),
        FILE("file"),
        FILE_COMMENT("file_comment");

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
 * This method pins a message to a particular conversation or channel.
 *
 * @property channel to pin the item in.
 * @property timestamp of the message to pin.
 */
data class PinsListRequest(
    @JsonProperty("channel") val channel: String,
    @JsonProperty("timestamp") val timestamp: Instant
) {
    companion object
}
