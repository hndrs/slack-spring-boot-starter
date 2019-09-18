package com.kreait.slack.broker.store.user

/**
 * In Memory TeamStore
 * This Default implementation should not be used on production environments
 */
class InMemoryUserStore(private val inMemoryUsers: MutableMap<String, User> = mutableMapOf()) : UserStore {
    override fun findByTeam(teamId: String): List<User> {
        return inMemoryUsers.filterValues { it.teamId == teamId }.values.toList()
    }

    override fun findById(id: String): User {
        return inMemoryUsers[id] ?: throw UserNotFoundException("User with id $id not found")
    }

    override fun put(vararg users: User) {
        users.forEach { user ->
            inMemoryUsers[user.id] = user
        }

    }

    override fun removeById(id: String) {
        inMemoryUsers.remove(id)
    }
}
