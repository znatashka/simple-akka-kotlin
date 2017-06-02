package ru.u26c4.demo

import akka.actor.ActorSystem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class ComletableFutureService {

    @Autowired
    var actorSystem: ActorSystem? = null

    @Autowired
    var springExtension: SpringExtension? = null

    fun get(payload: String, id: Long): CompletableFuture<Message> {
        val future = CompletableFuture<Message>()
        val workerActor = actorSystem?.actorOf(springExtension?.props("workerActor", future), "worker-actor")
        workerActor?.tell(Message(payload, id), null)
        return future
    }
}