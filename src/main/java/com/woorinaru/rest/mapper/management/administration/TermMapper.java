package com.woorinaru.rest.mapper.management.administration;

import com.woorinaru.rest.dto.management.administration.Term;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.woorinaru.core.model.management.administration.Event;
import com.woorinaru.core.model.user.Staff;

@Mapper
public interface TermMapper {

    TermMapper MAPPER = Mappers.getMapper(TermMapper.class);

    Term mapToDto(com.woorinaru.core.model.management.administration.Term termModel);

    com.woorinaru.core.model.management.administration.Term mapToModel(Term term);

    default Integer mapToDto(Staff staffModel) {
        return staffModel.getId();
    }

    default Staff mapToStaffModel(Integer id) {
        Staff staffModel = new Staff();
        staffModel.setId(id);
        return staffModel;
    }

    default Integer mapToDto(Event eventModel) {
        return eventModel.getId();
    }

    default Event mapToEventModel(Integer id) {
        Event eventModel = new Event();
        eventModel.setId(id);
        return eventModel;
    }
}
