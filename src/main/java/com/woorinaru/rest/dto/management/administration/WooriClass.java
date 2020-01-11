package com.woorinaru.rest.dto.management.administration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public abstract class WooriClass {

    protected int id;
    protected Collection<Integer> resources;
    protected Collection<Integer> staff;
    protected Collection<Integer> students;
    protected int event;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createDateTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updateDateTime;

    public WooriClass() {}

    public Collection<Integer> getResources() {
        return resources;
    }

    public Collection<Integer> getStaff() {
        return staff;
    }

    public Collection<Integer> getStudents() {
        return students;
    }

    public void setResources(Collection<Integer> resources) {
        this.resources = resources;
    }

    public void setStaff(Collection<Integer> staff) {
        this.staff = staff;
    }

    public void setStudents(Collection<Integer> students) {
        this.students = students;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addResource(Integer resource) {
        if (resources == null) {
            resources = new ArrayList<>();
        }
        return resources.add(resource);
    }

    public boolean addStaff(Integer staff) {
        if (this.staff == null) {
            this.staff = new ArrayList<>();
        }
        return this.staff.add(staff);
    }

    public boolean addStudent(Integer student) {
        if (students == null) {
            this.students = new ArrayList<>();
        }
        return this.students.add(student);
    }

    public boolean removeResource(Integer id) {
        if (resources == null) {
            return false;
        }
        return resources.removeIf(resource -> resource == id);
    }

    public boolean removeStaff(Integer id) {
        if (staff == null) {
            return false;
        }
        return this.staff.removeIf(staff -> staff == id);
    }

    public boolean removeStudent(Integer id) {
        if (students == null) {
            return false;
        }
        return this.students.removeIf(student -> student == id);
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public abstract Grade getGrade();

}
