package ru.u26c4.demo

import akka.actor.ActorSystem
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ApplicationConfiguration {

    @Autowired
    var applicationContext: ApplicationContext? = null

    @Autowired
    var springExtension: SpringExtension? = null

    @Bean
    fun actorSystem(): ActorSystem {
        val system = ActorSystem.create("demo-actor-system", akkaConfiguration())
        springExtension?.initialize(applicationContext!!)
        return system
    }

    @Bean
    fun akkaConfiguration(): Config {
        return ConfigFactory.load()
    }
}