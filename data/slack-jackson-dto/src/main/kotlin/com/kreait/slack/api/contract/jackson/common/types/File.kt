package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class File(
    @JsonProperty("id") val id: String,
    @InstantToString @JsonProperty("created") val created: Instant?,
    @InstantToString @JsonProperty("timestamp") val timestamp: Instant?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("mimetype") val mimetype: String?,
    @JsonProperty("filetype") val filetype: String?,
    @JsonProperty("pretty_type") val prettyType: String?,
    @JsonProperty("user") val userId: String?,
    @JsonProperty("editable") val isEditable: Boolean?,
    @JsonProperty("size") val size: Long?,
    @JsonProperty("mode") val mode: String?,
    @JsonProperty("is_external") val isExternal: Boolean?,
    @JsonProperty("external_type") val externalType: String?,
    @JsonProperty("is_public") val isPublic: Boolean?,
    @JsonProperty("public_url_shared") val isPublicUrlShared: Boolean?,
    @JsonProperty("display_as_bot") val displayAsBot: Boolean?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("url_private") val urlPrivate: String?,
    @JsonProperty("url_private_download") val urlPrivateDownload: String?,
    @JsonProperty("thumb_64") val thumb64: String?,
    @JsonProperty("thumb_80") val thumb80: String?,
    @JsonProperty("thumb_360") val thumb360: String?,
    @JsonProperty("thumb_360_w") val thumb360Width: Int?,
    @JsonProperty("thumb_360_h") val thumb360Height: Int?,
    @JsonProperty("thumb_160") val thumb160: String?,
    @JsonProperty("thumb_360_gif") val thumb360Gif: String?,
    @JsonProperty("image_exif_rotation") val imageExifRotation: Int?,
    @JsonProperty("original_w") val originalWidth: Int?,
    @JsonProperty("original_h") val originalHeight: Int?,
    @JsonProperty("deanimate_gif") val deanimateGif: String?,
    @JsonProperty("pjpeg") val pjpeg: String?,
    @JsonProperty("permalink") val permalink: String?,
    @JsonProperty("permalink_public") val permalinkPublic: String?,
    @JsonProperty("comments_count") val commentsCount: Int?,
    @JsonProperty("is_starred") val isStarred: Boolean?,
    @JsonProperty("shares") val shares: Shares?,
    @JsonProperty("channels") val channelIds: List<String>?,
    @JsonProperty("groups") val groupIds: List<String>?,
    @JsonProperty("ims") val imIds: List<String>?,
    @JsonProperty("has_rich_preview") val hasRichPreview: Boolean?,
    @JsonProperty("pinned_to") val pinnedTo: List<String>?
)
