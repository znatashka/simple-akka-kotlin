package ru.u26c4.demo

import akka.actor.Extension
import akka.actor.Props
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class SpringExtension : Extension {

    var applicationContext: ApplicationContext = null!!

    fun initialize(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    fun props(actorBeanName: String): Props {
        return Props.create(SpringActorProducer::class.java, applicationContext, actorBeanName)
    }
}