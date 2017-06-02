package ru.u26c4.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.util.concurrent.atomic.AtomicLong

@RestController
open class DeferredResultController {

    val DEFFERED_RESULT_TIMEOUT = 1000L

    var id: AtomicLong = AtomicLong(0)

    @Autowired
    var completableFutureService: ComletableFutureService? = null

    @RequestMapping("/async-non-blocking")
    open fun getAsyncNonBlocking(): DeferredResult<Message> {
        val deferred = DeferredResult<Message>(DEFFERED_RESULT_TIMEOUT)
        val future = completableFutureService?.get("async-non-blocking", id.getAndIncrement())

        future?.whenComplete({ result, error -> if (error != null) deferred.setErrorResult(error) else deferred.setResult(result) })

        return deferred
    }
}