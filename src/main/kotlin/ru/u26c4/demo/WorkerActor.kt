package ru.u26c4.demo

import akka.actor.UntypedAbstractActor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class WorkerActor : UntypedAbstractActor() {

    @Autowired
    var businessService: BusinessService? = null

    var count = 0

    override fun onReceive(message: Any?) {
        when (message) {
            is Request -> businessService?.perform("${this} ${++count}")
            is Response -> sender().tell(count, self())
            else -> unhandled(message)
        }
    }
}