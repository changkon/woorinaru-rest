package com.woorinaru.rest.dto.management.administration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class Event {

    private int id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;
    private String address;
    private String description;
    private Collection<Event.WooriClass> wooriClasses;
    private Collection<Integer> studentReservations;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDateTime;

    public Event() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWooriClasses(Collection<Event.WooriClass> wooriClasses) {
        this.wooriClasses = wooriClasses;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Event.WooriClass> getWooriClasses() {
        return wooriClasses;
    }

    public Collection<Integer> getStudentReservations() {
        return studentReservations;
    }

    public void setStudentReservations(Collection<Integer> studentReservations) {
        this.studentReservations = studentReservations;
    }

    public boolean addWooriClass(Event.WooriClass wooriClass) {
        if (wooriClasses == null) {
            this.wooriClasses = new ArrayList<>();
        }
        return this.wooriClasses.add(wooriClass);
    }

    public boolean addStudentReservation(Integer student) {
        if (studentReservations == null) {
            this.studentReservations = new ArrayList<>();
        }

        return this.studentReservations.add(student);
    }

    public boolean removeWooriClass(int id) {
        if (wooriClasses == null) {
            return false;
        }
        return this.wooriClasses.removeIf(wClass -> wClass.getId() == id);
    }

    public boolean removeStudentReservation(int id) {
        if (studentReservations == null) {
            return false;
        }
        return this.studentReservations.removeIf(student -> student == id);
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

    public static class WooriClass {
        private int id;
        private Grade grade;

        public WooriClass() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Grade getGrade() {
            return grade;
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }
    }

}
