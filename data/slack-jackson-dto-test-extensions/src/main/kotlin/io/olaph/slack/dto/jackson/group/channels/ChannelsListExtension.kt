package io.olaph.slack.dto.jackson.group.channels

fun SuccessfulGetChannelListResponse.Companion.sample(): SuccessfulGetChannelListResponse = SuccessfulGetChannelListResponse(true, listOf())

fun ErrorGetChannelListResponse.Companion.sample(): ErrorGetChannelListResponse = ErrorGetChannelListResponse(false, "")

fun Channel.Companion.sample(): Channel = Channel("",
        "",
        true,
        0,
        false,
        true,
        0,
        "",
        "",
        false,
        false,
        false,
        false,
        false,
        "",
        0,
        0,
        listOf(),
        Topic.sample(),
        Purpose.sample(),
        listOf(),
        1
)


fun Topic.Companion.sample(): Topic = Topic("", "", 0)

fun Purpose.Companion.sample(): Purpose = Purpose("", "", 0)
