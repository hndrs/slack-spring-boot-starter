package com.kreait.slack.broker.broker.common

import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Annotation that will set the cache to "no-store"
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DisableHttpCache

/**
 * Interceptor which sets Cache-Control: no-store and Edge-Control: no-store for methods annotated with [DisableHttpCache]
 *
 * Note: The interceptor DOES NOT override existing headers, instead when a cache-control header already exists in the response this interceptor does nothing
 */
class DisableHttpCacheInterceptor : HandlerInterceptorAdapter() {


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            setCacheControlHeader(handler, response)
        }
        return true
    }


    /**
     * Sets the "Cache-Control"-Header if cacheControl is not null
     *
     * @param handlerMethod the method which serves the response
     * @param response      the reponse where the header will be added
     */
    private fun setCacheControlHeader(handlerMethod: HandlerMethod, response: HttpServletResponse?) {
        if (hasHttpCacheDisabled(handlerMethod) && !response!!.containsHeader(HttpHeaders.CACHE_CONTROL)) {
            response.setHeader(HttpHeaders.CACHE_CONTROL, CacheControl.noStore().headerValue)
            response.setHeader("Edge-Control", CacheControl.noStore().headerValue)
        }
    }

    /**
     * Checks for [DisableHttpCache] annotation from the called method or declared class
     *
     * @param handlerMethod the method which serves the response
     * @return true if [DisableHttpCache] is present, false otherwise
     */
    private fun hasHttpCacheDisabled(handlerMethod: HandlerMethod): Boolean {
        return handlerMethod.getMethodAnnotation(DisableHttpCache::class.java) != null || handlerMethod.beanType.getAnnotation(DisableHttpCache::class.java) != null
    }
}
