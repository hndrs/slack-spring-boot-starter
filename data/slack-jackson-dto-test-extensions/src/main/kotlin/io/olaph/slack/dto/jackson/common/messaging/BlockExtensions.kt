package io.olaph.slack.dto.jackson.common.messaging

import io.olaph.slack.dto.jackson.common.messaging.composition.Text
import io.olaph.slack.dto.jackson.common.messaging.composition.sample

fun Block.Action.Companion.sample(): Block.Action = Block.Action(listOf(Element.Button.sample()))

fun Block.Image.Companion.sample(): Block.Image = Block.Image("https://via.placeholder.com/150", "altText")

fun Block.Context.Companion.sample(): Block.Context = Block.Context(listOf(Element.Button.sample()))

fun Block.Section.Companion.sample(): Block.Section = Block.Section(Text.sample())
