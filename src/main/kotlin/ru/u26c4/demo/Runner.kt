package ru.u26c4.demo

import akka.actor.ActorSystem
import akka.pattern.Patterns
import akka.util.Timeout
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

@Component
class Runner : CommandLineRunner {
    val log = LoggerFactory.getLogger(this.javaClass)!!

    @Autowired
    var actorSystem: ActorSystem? = null

    @Autowired
    var springExtension: SpringExtension? = null

    override fun run(vararg args: String?) {
        val workerActor = actorSystem?.actorOf(springExtension?.props("workerActor"), "worker-actor")

        workerActor?.tell(Request(), null)
        workerActor?.tell(Request(), null)
        workerActor?.tell(Request(), null)

        val duration = FiniteDuration.create(1, TimeUnit.SECONDS)
        val awaitable = Patterns.ask(workerActor, Response(), Timeout.durationToTimeout(duration))

        try {
            log.info("Response: {}", Await.result(awaitable, duration))
        } finally {
            actorSystem?.terminate()
            Await.result(actorSystem?.whenTerminated(), Duration.Inf())
        }
    }
}