package io.hndrs.slack.broker.store

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.store.user.FileUserStore
import io.hndrs.slack.broker.store.user.User
import io.hndrs.slack.broker.store.user.UserNotFoundException
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import java.io.File
import java.nio.charset.Charset

@ExtendWith(FileUserStoreTests.DisabledOnExistingUserStoreFile::class)
@DisplayName("LocalUserStore Test")
internal class FileUserStoreTests {

    companion object {
        private fun dataFile(): File = File(System.getProperty("user.home"), ".slack/user-store.json")

        private fun createFile() {
            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()
        }

        private fun deleteFile() {
            dataFile().deleteRecursively()
        }
    }

    @Nested
    @DisplayName("Initialization Tests")
    inner class InitializationTests {


        @DisplayName("File does not exist and no user home can be found")
        @Test
        fun fileDoesNotExistWhileNoUserHome() {
            //setup

            val userHome = System.getProperty("user.home")
            System.clearProperty("user.home")

            //test
            Assertions.assertThrows(Exception::class.java) { FileUserStore() }

            //cleanup
            System.setProperty("user.home", userHome)
        }

        @DisplayName("File does not exist")
        @Test
        fun fileDoesNotExist() {

            // setup
            FileUserStore()

            //test
            Assertions.assertTrue(dataFile().exists())
            val list: List<FileUserStore.LocalUser> = jacksonObjectMapper().readValue(dataFile())
            Assertions.assertEquals(listOf<FileUserStore.LocalUser>(), list)

            dataFile().deleteRecursively()
        }

        @DisplayName("File exist but is empty")
        @Test
        fun fileExistButEmpty() {

            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()

            // setup
            FileUserStore()

            //test
            Assertions.assertTrue(dataFile().length() == 2L)
            val list: List<FileUserStore.LocalUser> = jacksonObjectMapper().readValue(dataFile())
            Assertions.assertEquals(listOf<FileUserStore.LocalUser>(), list)

            dataFile().deleteRecursively()
        }

        @DisplayName("File exist not empty")
        @Test
        fun fileExistNotEmpty() {

            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()
            FileUtils.write(dataFile(), "[]", Charset.forName("UTF-8"))

            // setup
            FileUserStore()

            //test
            Assertions.assertTrue(dataFile().length() == 2L)
            val list: List<FileUserStore.LocalUser> = jacksonObjectMapper().readValue(dataFile())
            Assertions.assertEquals(listOf<FileUserStore.LocalUser>(), list)

            dataFile().deleteRecursively()
        }

        @DisplayName("File exist but is directory")
        @Test
        fun fileExistButIsDirectory() {

            // setup
            dataFile().mkdirs()

            //test
            Assertions.assertThrows(IllegalStateException::class.java) { FileUserStore() }


            dataFile().deleteRecursively()
        }

    }

    @Nested
    @DisplayName("Operation Tests")
    inner class OperationTests {


        @Test
        @DisplayName("Exception On Non Existent User")
        fun findNonExistentUser() {

            createFile()

            // test
            Assertions.assertThrows(UserNotFoundException::class.java) { FileUserStore().findById(User.sample().id) }

            //cleanup
            deleteFile()
        }

        @Test
        @DisplayName("Add User")
        fun addUser() {
            // setup
            createFile()
            FileUserStore().put(User.sample())

            // test
            Assertions.assertEquals(User.sample(), FileUserStore().findById(User.sample().id))

            //cleanup
            deleteFile()
        }

        @Test
        @DisplayName("Find User by Team")
        fun findByTeam() {
            // setup
            createFile()
            FileUserStore().put(User.sample().copy(id = "testuser1", teamId = "team1"))
            FileUserStore().put(User.sample().copy(id = "testuser2", teamId = "team2"))

            Assertions.assertEquals(1, FileUserStore().findByTeam("team1").size)

            //cleanup
            deleteFile()
        }


        @DisplayName("Test Update")
        @Test
        fun testUpdate() {
            createFile()
            val store = FileUserStore()
            store.put(User.sample().copy(id = "test", name = "tester"))

            // test
            store.update(User.sample().copy(id = "test", name = "tester123"))

            Assertions.assertEquals("tester123", store.findById("test").name)

            //cleanup
            deleteFile()
        }

        @DisplayName("Remove Non Existent")
        @Test
        fun removeNonExistent() {
            //setup
            createFile()
            val localUserStore = FileUserStore()

            //test
            Assertions.assertDoesNotThrow { localUserStore.update(User.sample().copy(id = "missingUserID")) }

            //cleanup
            deleteFile()
        }
    }

    class DisabledOnExistingUserStoreFile : ExecutionCondition {

        override fun evaluateExecutionCondition(context: ExtensionContext?): ConditionEvaluationResult {
            return if (dataFile().exists()) {
                ConditionEvaluationResult.disabled("UserStore File Already Present")
            } else {
                ConditionEvaluationResult.enabled("No UserStore File Present")
            }
        }

    }

}
