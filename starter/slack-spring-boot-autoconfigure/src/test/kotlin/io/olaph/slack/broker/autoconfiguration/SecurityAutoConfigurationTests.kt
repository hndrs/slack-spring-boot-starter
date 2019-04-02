package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.security.VerificationHandlerInterceptor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@DisplayName("Test Security Autoconfiguration")
class SecurityAutoConfigurationTests {

    @DisplayName("Verification interceptor Registration Test")
    @Test
    fun verificationInterceptorRegistration() {
        ApplicationContextRunner()
                .withSystemProperties("slack.security.signing-secret:mysecret")
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    val registry = object : InterceptorRegistry() {
                        public override fun getInterceptors(): MutableList<Any> {
                            return super.getInterceptors()
                        }
                    }
                    val bean = it.getBean<WebMvcConfigurer>("io.olaph.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration\$SecurityConfiguration")

                    bean.addInterceptors(registry)
                    Assertions.assertTrue(registry.interceptors[0] is VerificationHandlerInterceptor)
                }

        ApplicationContextRunner()
                .withSystemProperties("slack.security.signing-secret:")
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    assertThrows<NoSuchBeanDefinitionException> { it.getBean<WebMvcConfigurer>("io.olaph.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration\$SecurityConfiguration") }
                }
    }
}