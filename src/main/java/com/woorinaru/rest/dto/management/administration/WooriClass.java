package com.woorinaru.rest.dto.management.administration;

import java.util.ArrayList;
import java.util.Collection;

public abstract class WooriClass {

    protected int id;
    protected Collection<Integer> resources;
    protected Collection<Integer> staff;
    protected Collection<Integer> students;
    protected int event;

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

    public abstract Grade getGrade();

}
