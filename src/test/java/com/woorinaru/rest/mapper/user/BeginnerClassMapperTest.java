package com.woorinaru.rest.mapper.user;

import com.woorinaru.rest.dto.management.administration.Grade;
import com.woorinaru.rest.mapper.management.administration.BeginnerClassMapper;
import org.junit.jupiter.api.Test;
import com.woorinaru.core.model.management.administration.BeginnerClass;
import com.woorinaru.core.model.management.administration.Event;
import com.woorinaru.core.model.management.administration.Resource;
import com.woorinaru.core.model.user.Staff;
import com.woorinaru.core.model.user.Student;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class BeginnerClassMapperTest {

    @Test
    public void testBeginnerClassModelIsMappedToDto() {
        // GIVEN
        BeginnerClass beginnerClassModel = new BeginnerClass();
        beginnerClassModel.setId(1);

        Event eventModel = new Event();
        eventModel.setId(1);
        eventModel.setDescription("event description");

        Resource resourceModel = new Resource();
        resourceModel.setId(1);
        resourceModel.setDescription("resource description");

        Staff staffModel1 = new Staff();
        staffModel1.setId(1);
        staffModel1.setName("staff 1");

        Staff staffModel2 = new Staff();
        staffModel2.setId(2);
        staffModel2.setName("staff 2");

        Student student = new Student();
        student.setId(1);
        student.setName("student name");

        beginnerClassModel.setEvent(eventModel);
        beginnerClassModel.setResources(List.of(resourceModel));
        beginnerClassModel.setStaff(List.of(staffModel1, staffModel2));
        beginnerClassModel.setStudents(List.of(student));

        // WHEN
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        com.woorinaru.rest.dto.management.administration.BeginnerClass beginnerClassDto = mapper.mapToDto(beginnerClassModel);

        // THEN
        assertThat(beginnerClassDto.getId()).isEqualTo(1);
        assertThat(beginnerClassDto.getGrade()).isEqualTo(Grade.BEGINNER);
        assertThat(beginnerClassDto.getEvent()).isEqualTo(1);
        assertThat(beginnerClassDto.getResources()).contains(1);
        assertThat(beginnerClassDto.getStaff()).contains(1, 2);
        assertThat(beginnerClassDto.getStudents()).contains(1);
    }

    @Test
    public void testBeginnerClassDtoIsMappedToModel() {
        // GIVEN
        com.woorinaru.rest.dto.management.administration.BeginnerClass beginnerClassDto = new com.woorinaru.rest.dto.management.administration.BeginnerClass();
        beginnerClassDto.setId(1);

        beginnerClassDto.setEvent(2);
        beginnerClassDto.setResources(List.of(1, 2));
        beginnerClassDto.setStaff(List.of(10));
        beginnerClassDto.setStudents(List.of(8, 9));

        // WHEN
        BeginnerClassMapper mapper = BeginnerClassMapper.MAPPER;
        BeginnerClass beginnerClassModel = mapper.mapToModel(beginnerClassDto);

        // THEN
        assertThat(beginnerClassModel.getId()).isEqualTo(1);
        assertThat(beginnerClassModel.getGrade()).isEqualTo(com.woorinaru.core.model.management.administration.Grade.BEGINNER);
        assertThat(beginnerClassModel.getEvent()).matches(e -> e.getId() == 2);
        assertThat(beginnerClassModel.getEvent()).matches(e -> Objects.isNull(e.getDescription()));
        assertThat(beginnerClassModel.getResources()).hasSize(2);
        assertThat(beginnerClassModel.getResources()).anyMatch(r -> r.getId() == 1 && r.getDescription() == null);
        assertThat(beginnerClassModel.getResources()).anyMatch(r -> r.getId() == 2 && r.getDescription() == null);
        assertThat(beginnerClassModel.getStaff()).hasSize(1);
        assertThat(beginnerClassModel.getStaff()).anyMatch(staff -> staff.getId() == 10);
        assertThat(beginnerClassModel.getStudents()).hasSize(2);
        assertThat(beginnerClassModel.getStudents()).anyMatch(student -> student.getId() == 8 && student.getName() == null);
        assertThat(beginnerClassModel.getStudents()).anyMatch(student -> student.getId() == 9 && student.getName() == null);
    }

}
