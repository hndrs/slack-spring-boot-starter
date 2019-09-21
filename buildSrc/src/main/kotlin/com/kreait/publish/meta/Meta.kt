package com.kreait.publish.meta

data class Developer(val id: String, val name: String, val email: String)
data class Contributor(val name: String, val email: String, val url: String? = null)
data class Organization(val name: String, val url: String)
data class Scm(val connection: String, val url: String)
data class License(val url: String, val name: String)
