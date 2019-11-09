package com.woorinaru.rest.controller;

import com.woorinaru.rest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.woorinaru.rest.dto.management.administration.Event;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable int id) {
        Event eventDto = this.eventService.get(id);
        return ResponseEntity.ok().body(eventDto);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> eventDtos = this.eventService.getAll();
        return ResponseEntity.ok().body(eventDtos);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Event event) {
        int generatedId = this.eventService.create(event);
        String uri = String.format("/woorinaru/api/event/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Event event = new Event();
        event.setId(id);
        this.eventService.delete(event);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody Event event) {
        this.eventService.modify(event);
        return ResponseEntity.ok().build();
    }

}
