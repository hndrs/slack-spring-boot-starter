package io.hndrs.slack.broker.interactive

import com.google.gson.annotations.SerializedName
import com.slack.api.model.Message
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.composition.ConfirmationDialogObject
import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.composition.PlainTextObject
import com.slack.api.model.view.View
import com.slack.api.model.view.ViewState

data class BlockActionPayload(
    val enterprise: Enterprise? = null,
    val team: Team,
    val user: User? = null,
    val apiAppId: String? = null,
    val token: String? = null,
    val container: Container? = null,
    val triggerId: String? = null,
    val channel: Channel? = null,
    val message: Message? = null,
    val responseUrl: String? = null,
    val view: View? = null,
    val state: ViewState? = null, // for actions in a message
    val appUnfurl: AppUnfurl? = null,
    val actions: List<Action>? = null,
    val isEnterpriseInstall: Boolean = false,
) {
    data class Enterprise(
        val id: String,
        val name: String? = null,
    )

    data class Team(
        val id: String,
        val domain: String? = null,
        val enterpriseId: String? = null,
        val enterpriseName: String? = null,
    )

    data class User(
        val id: String,
        val username: String? = null,
        val name: String? = null,
        val teamId: String? = null,
    )

    data class Container(
        val type: String? = null,
        val messageTs: String? = null,
        val attachmentId: Int? = null,
        val channelId: String? = null,
        val viewId: String? = null,
        val text: String? = null,

        @SerializedName("is_ephemeral")
        val ephemeral: Boolean = false,

        @SerializedName("is_app_unfurl")
        val appUnfurl: Boolean = false,
        val appUnfurlUrl: String? = null,
    )

    data class Channel(
        val id: String,
        val name: String? = null,
    )

    data class AppUnfurl(
        val id: Int,
        val blocks: List<LayoutBlock>? = null,
        val fallback: String? = null,
        val botId: String? = null,
        val appUnfurlUrl: String? = null,
        @SerializedName("is_app_unfurl")
        val appUnfurl: Boolean = false,
        val appId: String? = null,
    )

    data class Action(
        val actionId: String? = null,
        val blockId: String? = null,
        val text: Text? = null,
        val value: String? = null,
        val type: String? = null,
        val style: String? = null,
        val actionTs: String? = null,

        // common fields
        val placeholder: PlainTextObject? = null,
        val confirm: ConfirmationDialogObject? = null,

        // button
        val url: String? = null,

        // static_select
        val initialOption: OptionObject? = null,
        val selectedOption: SelectedOption? = null, // overflow

        // users_select
        val selectedUser: String? = null,
        val initialUser: String? = null,

        // conversations_select
        val selectedConversation: String? = null,
        val initialConversation: String? = null,

        // channels_select
        val selectedChannel: String? = null,
        val initialChannel: String? = null,

        // external_select
        val minQueryLength: Int? = null,

        // datepicker
        val selectedDate: String? = null,
        val initialDate: String? = null,

        // timepicker
        val selectedTime: String? = null,
        val initialTime: String? = null,

        // multi_static_select
        // multi_external_select
        val initialOptions: List<OptionObject>? = null,
        val selectedOptions: List<SelectedOption>? = null,

        // multi_users_select
        val initialUsers: List<String>? = null,
        val selectedUsers: List<String>? = null,

        // multi_conversations_select
        val initialConversations: List<String>? = null,
        val selectedConversations: List<String>? = null,

        // multi_channels_select
        val initialChannels: List<String>? = null,
        val selectedChannels: List<String>? = null,
    ) {

        data class Text(
            val type: String,
            val text: String? = null,
            val emoji: Boolean = false,
        )

        data class SelectedOption(
            val text: PlainTextObject? = null,
            val value: String? = null,
        )
    }

    companion object {
        const val TYPE = "block_actions"
    }
}
