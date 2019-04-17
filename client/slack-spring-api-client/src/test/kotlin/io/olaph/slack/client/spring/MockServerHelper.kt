package io.olaph.slack.client.spring

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators
import org.springframework.web.client.RestTemplate
import java.net.URI

class MockServerHelper() {
    companion object {

        private fun getObjectString(response: Any): String {
            val mapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
            return mapper.writeValueAsString(response)
        }

        /**
         * builds a mocked RestServer that responds with the defined [responseBody] when the
         * [methodEndpoint] is called on the [restTemplate]
         */
        fun buildMockRestServer(restTemplate: RestTemplate, responseBody: Any, methodEndpoint: String): MockRestServiceServer {
            val mockserver = MockRestServiceServer.createServer(restTemplate)
            mockserver.expect(ExpectedCount.once(),
                    MockRestRequestMatchers.requestTo(URI("https://slack.com/api/$methodEndpoint")))
                    .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                    .andRespond(MockRestResponseCreators.withSuccess()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(getObjectString(responseBody)))
            return mockserver
        }
    }
}