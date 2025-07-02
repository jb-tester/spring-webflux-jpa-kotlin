package org.example.demoktmv.events

import org.springframework.context.ApplicationEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service


@Service
class EventsListener {

    @EventListener
    suspend fun handleApplicationEvent(event: ApplicationEvent) {
        println("!!!! Received an event: $event")
    }


}