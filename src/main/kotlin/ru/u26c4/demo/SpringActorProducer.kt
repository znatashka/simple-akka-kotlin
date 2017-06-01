package ru.u26c4.demo

import akka.actor.Actor
import akka.actor.IndirectActorProducer
import org.springframework.context.ApplicationContext

open class SpringActorProducer(var applicationContext: ApplicationContext, var actorBeanName: String) : IndirectActorProducer {

    override fun actorClass(): Class<out Actor> {
        return applicationContext.getType(actorBeanName) as Class<out Actor>
    }

    override fun produce(): Actor {
        return applicationContext.getBean(actorBeanName) as Actor
    }
}