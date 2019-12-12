package com.kreait.slack.broker.data.user

import com.kreait.slack.broker.data.DataController
import com.kreait.slack.broker.store.user.User
import com.kreait.slack.broker.store.user.UserStore
import org.springframework.web.bind.annotation.GetMapping

@DataController
class UserController(private val userStore: UserStore) {

    @GetMapping(path = ["/users"])
    fun users(): List<User> {
        return userStore.findAll();
    }
}
