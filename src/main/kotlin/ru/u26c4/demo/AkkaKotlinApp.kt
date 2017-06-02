package ru.u26c4.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
open class AkkaKotlinApp {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(AkkaKotlinApp::class.java)
        }
    }
}