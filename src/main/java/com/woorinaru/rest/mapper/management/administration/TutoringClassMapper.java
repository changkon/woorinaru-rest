package com.woorinaru.rest.mapper.management.administration;

import com.woorinaru.rest.dto.management.administration.Grade;
import com.woorinaru.rest.dto.management.administration.TutoringClass;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;
import com.woorinaru.core.model.management.administration.Event;
import com.woorinaru.core.model.management.administration.Resource;
import com.woorinaru.core.model.user.Staff;
import com.woorinaru.core.model.user.Student;

@Mapper
public interface TutoringClassMapper {

    TutoringClassMapper MAPPER = Mappers.getMapper(TutoringClassMapper.class);

    TutoringClass mapToDto(com.woorinaru.core.model.management.administration.TutoringClass tutoringClassModel);

    com.woorinaru.core.model.management.administration.TutoringClass mapToModel(TutoringClass tutoringClassDto);

    @ValueMappings({
        @ValueMapping(source="BEGINNER", target="BEGINNER"),
        @ValueMapping(source="INTERMEDIATE", target="INTERMEDIATE"),
        @ValueMapping(source="TUTORING", target="TUTORING"),
        @ValueMapping(source="OUTING", target="OUTING")
    })
    Grade mapToDto(com.woorinaru.core.model.management.administration.Grade gradeModel);

    default Integer mapToDto(Resource resourceModel) {
        return resourceModel.getId();
    }

    default Resource mapToResourceModel(Integer id) {
        Resource resourceModel = new Resource();
        resourceModel.setId(id);
        return resourceModel;
    }

    default Integer mapToDto(Staff staffModel) {
        return staffModel.getId();
    }

    default Staff mapToStaffModel(Integer id) {
        Staff staff = new Staff();
        staff.setId(id);
        return staff;
    }

    default Integer mapToDto(Student student) {
        return student.getId();
    }

    default Student mapToStudentModel(Integer id) {
        Student student = new Student();
        student.setId(id);
        return student;
    }

    default Integer mapToDto(Event event) {
        return event.getId();
    }

    default Event mapToEventModel(Integer id) {
        Event event = new Event();
        event.setId(id);
        return event;
    }

}
