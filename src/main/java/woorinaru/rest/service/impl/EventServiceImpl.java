package woorinaru.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorinaru.rest.dto.management.administration.BeginnerClass;
import woorinaru.rest.dto.management.administration.Event;
import woorinaru.rest.mapper.management.administration.EventMapper;
import woorinaru.rest.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private woorinaru.core.service.EventService eventService;

    @Override
    @Transactional
    public int create(Event event) {
        EventMapper mapper = EventMapper.MAPPER;
        woorinaru.core.model.management.administration.Event eventModel = mapper.mapToModel(event);
        return this.eventService.create(eventModel);
    }

    @Override
    @Transactional
    public Event get(int id) {
        woorinaru.core.model.management.administration.Event eventModel = this.eventService.get(id);
        EventMapper mapper = EventMapper.MAPPER;
        Event eventDto = mapper.mapToDto(eventModel);
        return eventDto;
    }

    @Override
    @Transactional
    public void delete(Event event) {
        EventMapper mapper = EventMapper.MAPPER;
        woorinaru.core.model.management.administration.Event eventModel = mapper.mapToModel(event);
        this.eventService.delete(eventModel);
    }

    @Override
    @Transactional
    public void modify(Event event) {
        EventMapper mapper = EventMapper.MAPPER;
        woorinaru.core.model.management.administration.Event eventModel = mapper.mapToModel(event);
        this.eventService.modify(eventModel);
    }

    @Override
    @Transactional
    public List<Event> getAll() {
        EventMapper mapper = EventMapper.MAPPER;
        List<woorinaru.core.model.management.administration.Event> eventModels = this.eventService.getAll();
        List<Event> eventDtos = eventModels.stream()
            .map(mapper::mapToDto)
            .collect(Collectors.toList());
        return eventDtos;
    }

    public woorinaru.core.service.EventService getEventService() {
        return eventService;
    }

    public void setEventService(woorinaru.core.service.EventService eventService) {
        this.eventService = eventService;
    }
}
