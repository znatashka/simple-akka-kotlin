package ru.u26c4.demo

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BusinessService {
    val log = LoggerFactory.getLogger(this.javaClass)!!

    fun perform(o: Any) {
        log.info("Perform: {}", o)
    }
}