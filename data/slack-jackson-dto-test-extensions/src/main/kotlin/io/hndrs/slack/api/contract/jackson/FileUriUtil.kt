package io.hndrs.slack.api.contract.jackson

import io.hndrs.slack.api.contract.jackson.group.users.ContextHolder
import java.net.JarURLConnection
import java.net.URI

fun getJarFileUri(filename: String): URI? {
    var uri = ContextHolder::class.java.classLoader.getResource(filename).toURI()
    if (uri.isOpaque && uri.toURL().openConnection() is JarURLConnection) {
        uri = (uri.toURL().openConnection() as JarURLConnection).jarFileURL.toURI()
    }
    return uri
}
