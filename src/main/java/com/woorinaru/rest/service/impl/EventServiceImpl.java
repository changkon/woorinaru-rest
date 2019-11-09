package com.woorinaru.rest.service.impl;

import com.woorinaru.rest.mapper.management.administration.EventMapper;
import com.woorinaru.rest.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woorinaru.rest.dto.management.administration.Event;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private com.woorinaru.core.service.EventService eventService;

    @Override
    @Transactional
    public int create(Event event) {
        EventMapper mapper = EventMapper.MAPPER;
        com.woorinaru.core.model.management.administration.Event eventModel = mapper.mapToModel(event);
        return this.eventService.create(eventModel);
    }

    @Override
    @Transactional
    public Event get(int id) {
        com.woorinaru.core.model.management.administration.Event eventModel = this.eventService.get(id);
        EventMapper mapper = EventMapper.MAPPER;
        Event eventDto = mapper.mapToDto(eventModel);
        return eventDto;
    }

    @Override
    @Transactional
    public void delete(Event event) {
        EventMapper mapper = EventMapper.MAPPER;
        com.woorinaru.core.model.management.administration.Event eventModel = mapper.mapToModel(event);
        this.eventService.delete(eventModel);
    }

    @Override
    @Transactional
    public void modify(Event event) {
        EventMapper mapper = EventMapper.MAPPER;
        com.woorinaru.core.model.management.administration.Event eventModel = mapper.mapToModel(event);
        this.eventService.modify(eventModel);
    }

    @Override
    @Transactional
    public List<Event> getAll() {
        EventMapper mapper = EventMapper.MAPPER;
        List<com.woorinaru.core.model.management.administration.Event> eventModels = this.eventService.getAll();
        List<Event> eventDtos = eventModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return eventDtos;
    }

    public com.woorinaru.core.service.EventService getEventService() {
        return eventService;
    }

    public void setEventService(com.woorinaru.core.service.EventService eventService) {
        this.eventService = eventService;
    }
}
