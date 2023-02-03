package io.hndrs.slack.broker.autoconfiguration

import com.slack.api.Slack
import io.hndrs.slack.broker.store.user.FileUserStore
import io.hndrs.slack.broker.store.user.InMemoryUserStore
import io.hndrs.slack.broker.store.user.User
import io.hndrs.slack.broker.store.user.UserInstallationReceiver
import io.hndrs.slack.broker.store.user.UserStore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class UserStoreAutoConfigurationTests {

    @DisplayName("Custom UserStore Registration")
    @Test
    fun userCustomStoreRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    UserStoreAutoConfiguration::class.java,
                    SlackConfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .withUserConfiguration(TestConfiguration::class.java)
            .run {
                Assertions.assertDoesNotThrow { it.getBean(UserStore::class.java) }
                Assertions.assertTrue(it.getBean(UserStore::class.java) is TestUserStore)
            }

        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    UserStoreAutoConfiguration::class.java,
                    SlackConfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .withUserConfiguration(TestConfiguration::class.java)
            .withPropertyValues("slack.store.user.type:memory")
            .run {
                Assertions.assertDoesNotThrow { it.getBean(UserStore::class.java) }
                Assertions.assertTrue(it.getBean(UserStore::class.java) is TestUserStore)
            }

        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    UserStoreAutoConfiguration::class.java,
                    SlackConfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .withUserConfiguration(TestConfiguration::class.java)
            .withPropertyValues("slack.store.user.type:file")
            .run {
                Assertions.assertDoesNotThrow { it.getBean(UserInstallationReceiver::class.java) }
                Assertions.assertDoesNotThrow { it.getBean(UserStore::class.java) }
                Assertions.assertTrue(it.getBean(UserStore::class.java) is TestUserStore)
            }
    }

    @DisplayName("UserManager Registration")
    @Test
    fun userManagerRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    UserStoreAutoConfiguration::class.java,
                    SlackConfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .run {
                Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(UserStore::class.java) }
                Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
                    it.getBean(
                        UserInstallationReceiver::class.java
                    )
                }
            }
    }

    @DisplayName("InMemoryUserStore Registration")
    @Test
    fun userStoreRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    UserStoreAutoConfiguration::class.java,
                    SlackConfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .withPropertyValues("slack.store.user.type:memory")
            .run {
                Assertions.assertDoesNotThrow { it.getBean(UserStore::class.java) }
                Assertions.assertTrue(it.getBean(UserStore::class.java) is InMemoryUserStore)
            }
    }

    @DisplayName("File UserStore Registration")
    @Test
    fun fileUserStoreRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    UserStoreAutoConfiguration::class.java,
                    SlackConfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .withPropertyValues("slack.store.user.type:file")
            .run {
                Assertions.assertDoesNotThrow { it.getBean(FileUserStore::class.java) }
                Assertions.assertTrue(it.getBean(UserStore::class.java) is FileUserStore)
            }
    }

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testUserStore(): UserStore = TestUserStore()
    }

    @Configuration
    open class SlackConfiguration {
        @Bean
        open fun slackClient(): Slack {
            return Slack()
        }
    }

    class TestUserStore : UserStore {
        override fun findByTeam(teamId: String, includeDeleted: Boolean): List<User> = throw NotImplementedError()

        override fun findById(id: String): User = throw NotImplementedError()

        override fun put(vararg users: User) {
            // stub
        }

        override fun update(newUser: User) {
            // stub
        }
    }
}
