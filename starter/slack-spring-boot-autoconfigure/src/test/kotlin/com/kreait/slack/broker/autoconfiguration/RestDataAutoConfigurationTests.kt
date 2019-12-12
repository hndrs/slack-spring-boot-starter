package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.data.event.EventController
import com.kreait.slack.broker.data.team.TeamController
import com.kreait.slack.broker.data.user.UserController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations

@DisplayName("Test RestData Autoconfiguration ")
class RestDataAutoConfigurationTests {


    @DisplayName("Team Controller Registration")
    @Test
    fun teamControllerRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(TeamStoreAutoconfiguration::class.java, UserStoreAutoConfiguration::class.java, SlackBrokerAutoConfiguration::class.java, RestDataAutoConfiguration::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamController::class.java) }
                    Assertions.assertDoesNotThrow { it.getBean(UserController::class.java) }
                    Assertions.assertDoesNotThrow { it.getBean(EventController::class.java) }
                }
    }
}
