package com.kreait.slack.broker.store

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kreait.slack.broker.extensions.sample
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

@ExtendWith(FileTeamStoreTests.DisabledOnExistingTeamStoreFile::class)
@DisplayName("LocalTeamStore Test")
internal class FileTeamStoreTests {

    companion object {
        private fun dataFile(): File = File(System.getProperty("user.home"), ".slack/team-store.json")

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
            Assertions.assertThrows(Exception::class.java) { FileTeamStore() }

            //cleanup
            System.setProperty("user.home", userHome)
        }

        @DisplayName("File does not exist")
        @Test
        fun fileDoesNotExist() {

            // setup
            FileTeamStore()

            //test
            Assertions.assertTrue(dataFile().exists())
            val list: List<FileTeamStore.LocalTeam> = jacksonObjectMapper().readValue(dataFile())
            Assertions.assertEquals(listOf<FileTeamStore.LocalTeam>(), list)

            dataFile().deleteRecursively()
        }

        @DisplayName("File exist but is empty")
        @Test
        fun fileExistButEmpty() {

            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()

            // setup
            FileTeamStore()

            //test
            Assertions.assertTrue(dataFile().length() == 2L)
            val list: List<FileTeamStore.LocalTeam> = jacksonObjectMapper().readValue(dataFile())
            Assertions.assertEquals(listOf<FileTeamStore.LocalTeam>(), list)

            dataFile().deleteRecursively()
        }

        @DisplayName("File exist not empty")
        @Test
        fun fileExistNotEmpty() {

            dataFile().parentFile.mkdirs()
            dataFile().createNewFile()
            FileUtils.write(dataFile(), "[]", Charset.forName("UTF-8"))

            // setup
            FileTeamStore()

            //test
            Assertions.assertTrue(dataFile().length() == 2L)
            val list: List<FileTeamStore.LocalTeam> = jacksonObjectMapper().readValue(dataFile())
            Assertions.assertEquals(listOf<FileTeamStore.LocalTeam>(), list)

            dataFile().deleteRecursively()
        }

        @DisplayName("File exist but is directory")
        @Test
        fun fileExistButIsDirectory() {

            // setup
            dataFile().mkdirs()

            //test
            Assertions.assertThrows(IllegalStateException::class.java) { FileTeamStore() }


            dataFile().deleteRecursively()
        }

    }

    @Nested
    @DisplayName("Operation Tests")
    inner class OperationTests {


        @Test
        @DisplayName("Exception On Non Existent Team")
        fun findNonExistentTeam() {

            createFile()

            // test
            Assertions.assertThrows(TeamNotFoundException::class.java) { FileTeamStore().findById(Team.sample().teamId) }

            //cleanup
            deleteFile()
        }

        @Test
        @DisplayName("Add Team")
        fun addTeam() {
            // setup
            createFile()
            FileTeamStore().put(Team.sample())

            // test
            Assertions.assertEquals(Team.sample(), FileTeamStore().findById(Team.sample().teamId))

            //cleanup
            deleteFile()
        }

        @DisplayName("Test Add and Remove")
        @Test
        fun removeTeam() {
            createFile()
            FileTeamStore().put(Team.sample())

            // test
            FileTeamStore().removeById(Team.sample().teamId)

            Assertions.assertThrows(TeamNotFoundException::class.java) { FileTeamStore().findById(Team.sample().teamId) }

            //cleanup
            deleteFile()
        }

        @DisplayName("Remove Non Existent")
        @Test
        fun removeNonExistent() {
            //setup
            createFile()
            val localTeamStore = FileTeamStore()

            //test
            Assertions.assertDoesNotThrow { localTeamStore.removeById("missingTeamID") }

            //cleanup
            deleteFile()
        }

    }

    class DisabledOnExistingTeamStoreFile : ExecutionCondition {

        override fun evaluateExecutionCondition(context: ExtensionContext?): ConditionEvaluationResult {
            return if (dataFile().exists()) {
                ConditionEvaluationResult.disabled("TeamStore File Already Present")
            } else {
                ConditionEvaluationResult.enabled("No TeamStore File Present")
            }
        }

    }

}
