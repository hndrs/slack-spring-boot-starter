package com.kreait.slack.broker.autoconfiguration.credentials

import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import java.io.File
import java.nio.charset.Charset

@ExtendWith(CredentialsFileCredentialsProviderTests.DisabledOnExistingCredentialsFile::class)
@DisplayName("CredentialsFileCredentialsProvider")
class CredentialsFileCredentialsProviderTests {


    @Test
    @DisplayName("Valid File On Home Path")
    @ExtendWith(DisabledOnExistingCredentialsFile::class)
    fun existingValidFile() {
        // setup

        val clientId = "sampleClientId"
        val clientSecret = "sampleClientSecret"
        val signingSecret = "sampleSigningSecret"

        val file = File("${System.getProperty("user.home")}/.slack/credentials")

        file.parentFile.mkdirs()
        file.createNewFile()
        FileUtils.write(file, "slack_app_client_id=$clientId\n" +
                "slack_app_client_secret=$clientSecret\n" +
                "slack_app_signing_secret=$signingSecret", Charset.forName("UTF-8"))


        // test
        val applicationCredentials = CredentialsFileCredentialsProvider()
                .applicationCredentials()


        // assert
        Assertions.assertEquals(clientId, applicationCredentials.clientId)
        Assertions.assertEquals(clientSecret, applicationCredentials.clientSecret)
        Assertions.assertEquals(signingSecret, applicationCredentials.signingSecret)

        // cleanup
        file.parentFile.deleteRecursively()
    }

    @Test
    @DisplayName("Invalid File On Home Path")
    @ExtendWith(DisabledOnExistingCredentialsFile::class)
    fun existingInvalidFile() {
        // setup

        val clientId = "sampleClientId"
        val signingSecret = "sampleSigningSecret"

        val file = File("${System.getProperty("user.home")}/.slack/credentials")

        file.parentFile.mkdirs()
        file.createNewFile()
        FileUtils.write(file, "slack_app_client_id=$clientId\n" +
                "slack_app_signing_secret=$signingSecret", Charset.forName("UTF-8"))

        // test and assert
        Assertions.assertThrows(ApplicationCredentialsException::class.java) {
            CredentialsFileCredentialsProvider().applicationCredentials()
        }


        // cleanup
        file.parentFile.deleteRecursively()
    }

    @Test
    @DisplayName("Invalid Home Path")
    fun couldNotObtainHomePath() {
        // setup

        val property = System.getProperty("user.home")
        System.clearProperty("user.home")
        // test and assert
        Assertions.assertThrows(ApplicationCredentialsException::class.java) {
            CredentialsFileCredentialsProvider().applicationCredentials()
        }


        // cleanup
        System.setProperty("user.home", property)
    }

    @Test
    @DisplayName("File does not exist")
    fun couldNotObtainFile() {
        Assertions.assertThrows(ApplicationCredentialsException::class.java) {
            CredentialsFileCredentialsProvider().applicationCredentials()
        }
    }

    class DisabledOnExistingCredentialsFile : ExecutionCondition {

        override fun evaluateExecutionCondition(context: ExtensionContext?): ConditionEvaluationResult {
            return if (File("${System.getProperty("user.home")}/.slack/credentials").exists()) {
                ConditionEvaluationResult.disabled("Credentials File Already Present")
            } else {
                ConditionEvaluationResult.enabled("No Credentials File Present")
            }
        }

    }
}
