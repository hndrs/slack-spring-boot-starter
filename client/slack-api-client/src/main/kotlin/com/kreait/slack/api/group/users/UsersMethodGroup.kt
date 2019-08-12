package com.kreait.slack.api.group.users

interface UsersMethodGroup {

    fun info(authToken: String): UsersInfoMethod

    fun list(authToken: String): UserListMethod

    fun conversations(authToken: String): UserConversationsMethod

    fun deletePhoto(authToken: String): UsersDeletePhotoMethod

    fun identity(authToken: String): UsersIdentityMethod

    fun getProfile(authToken: String): UsersGetProfileMethod

    fun setProfile(authToken: String): UsersSetProfileMethod

    fun setPresence(authToken: String): UsersSetPresenceMethod

    fun setPhoto(authToken: String): UsersSetPhotoMethod

    fun getPresence(authToken: String): UsersGetPresenceMethod

    fun lookupByEmail(authToken: String): UsersLookupByEmailMethod
}
