package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.store.team.FileTeamStore
import com.kreait.slack.broker.store.team.InMemoryTeamStore
import com.kreait.slack.broker.store.team.Team
import com.kreait.slack.broker.store.team.TeamStore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class TeamStoreAutoConfigurationTests {

    @DisplayName("Custom TeamStore Registration")
    @Test
    fun teamCustomStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is TestTeamStore)
                }

        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .withPropertyValues("slack.store.team.type:memory")
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is TestTeamStore)
                }

        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .withPropertyValues("slack.store.team.type:file")
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is TestTeamStore)
                }
    }

    @DisplayName("InMemoryTeamStore Registration")
    @Test
    fun teamStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withPropertyValues("slack.store.team.type:memory")
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is InMemoryTeamStore)
                }
    }

    @DisplayName("File TeamStore Registration")
    @Test
    fun fileTeamStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withPropertyValues("slack.store.team.type:file")
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(FileTeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is FileTeamStore)
                }
    }


    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testTeamStore(): TeamStore = TestTeamStore()
    }

    class TestTeamStore : TeamStore {

        override fun findById(id: String): Team = throw NotImplementedError()

        override fun put(team: Team) {}

        override fun removeById(id: String) {}
        override fun findAll(): List<Team> = listOf()


    }

}
