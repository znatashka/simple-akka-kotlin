package ru.u26c4.demo

import akka.actor.Actor
import akka.actor.IndirectActorProducer
import org.springframework.context.ApplicationContext

@Suppress("UNCHECKED_CAST")
open class SpringActorProducer(var applicationContext: ApplicationContext, var actorBeanName: String, vararg var args: Any) : IndirectActorProducer {

    override fun actorClass(): Class<out Actor> {
        return applicationContext.getType(actorBeanName) as Class<out Actor>
    }

    override fun produce(): Actor {
        return if (args.isEmpty()) applicationContext.getBean(actorBeanName) as Actor
        else applicationContext.getBean(actorBeanName, args[0]) as Actor
    }
}