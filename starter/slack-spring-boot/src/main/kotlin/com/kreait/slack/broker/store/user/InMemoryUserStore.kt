package com.kreait.slack.broker.store.user

/**
 * In Memory UserStore
 * This Default implementation should not be used on production environments
 */
class InMemoryUserStore(private val inMemoryUsers: MutableMap<String, User> = mutableMapOf()) : UserStore {
    override fun findByTeam(teamId: String, includeDeleted: Boolean): List<User> {
        return inMemoryUsers.filterValues { it.teamId == teamId && !it.isDeleted }.values.toList()
    }

    override fun findById(id: String): User {
        return inMemoryUsers[id] ?: throw UserNotFoundException("User with id $id not found")
    }

    override fun put(vararg users: User) {
        users.forEach { user ->
            inMemoryUsers[user.id] = user
        }

    }

    override fun update(newUser: User) {
        inMemoryUsers.replace(newUser.id, newUser)
    }

    override fun findAll(): List<User> = inMemoryUsers.entries.map { it.value }

}
