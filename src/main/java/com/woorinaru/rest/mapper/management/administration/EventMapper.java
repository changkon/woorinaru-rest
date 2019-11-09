package com.woorinaru.rest.mapper.management.administration;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.woorinaru.core.model.management.administration.*;
import com.woorinaru.core.model.user.Student;
import com.woorinaru.rest.dto.management.administration.Event;
import com.woorinaru.rest.dto.management.administration.Grade;

@Mapper
public interface EventMapper {

    EventMapper MAPPER = Mappers.getMapper(EventMapper.class);

    Event mapToDto(com.woorinaru.core.model.management.administration.Event eventModel);

    com.woorinaru.core.model.management.administration.Event mapToModel(Event eventDto);

    default Event.WooriClass mapToDto(WooriClass wooriClass) {
        Event.WooriClass wooriClassDto = new Event.WooriClass();
        wooriClassDto.setId(wooriClass.getId());
        wooriClassDto.setGrade(mapToDto(wooriClass.getGrade()));
        return wooriClassDto;
    }

    default WooriClass mapToWooriClassModel(Event.WooriClass wooriClassDto) {
        WooriClass wooriClassModel = mapToModel(wooriClassDto.getGrade());
        wooriClassModel.setId(wooriClassDto.getId());
        return wooriClassModel;
    }

    default Integer mapToDto(Student student) {
        return student.getId();
    }

    default Student mapToModel(Integer id) {
        Student studentModel = new Student();
        studentModel.setId(id);
        return studentModel;
    }

    default Grade mapToDto(com.woorinaru.core.model.management.administration.Grade gradeModel) {
        switch(gradeModel) {
            case BEGINNER:
                return Grade.BEGINNER;
            case INTERMEDIATE:
                return Grade.INTERMEDIATE;
            case OUTING:
                return Grade.OUTING;
            case TUTORING:
                return Grade.TUTORING;
            default:
                throw new IllegalArgumentException("Invalid Grade");
        }
    }

    default WooriClass mapToModel(Grade gradeDto) {
        switch(gradeDto) {
            case BEGINNER:
                return new BeginnerClass();
            case INTERMEDIATE:
                return new IntermediateClass();
            case OUTING:
                return new OutingClass();
            case TUTORING:
                return new TutoringClass();
            default:
                throw new IllegalArgumentException("Invalid Grade");
        }
    }
}
