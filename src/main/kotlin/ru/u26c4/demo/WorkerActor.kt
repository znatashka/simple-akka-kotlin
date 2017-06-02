package ru.u26c4.demo

import akka.actor.UntypedAbstractActor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component("workerActor")
@Scope("prototype")
class WorkerActor(val future: CompletableFuture<Message>) : UntypedAbstractActor() {

    @Autowired
    var businessService: BusinessService? = null

    override fun onReceive(message: Any?) {
        businessService?.perform("$this $message")

        when (message) {
            is Message -> future.complete(message)
            else -> unhandled(message)
        }

        context().stop(self())
    }
}