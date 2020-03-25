package com.kreait.slack.broker.data

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/data")
annotation class DataController {

}
