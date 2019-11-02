package woorinaru.rest.dto.user;

import woorinaru.rest.dto.management.administration.Team;

public class Staff extends User {

    private StaffRole staffRole;
    private Team team;

    public Staff() {}

    public StaffRole getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(StaffRole staffRole) {
        this.staffRole = staffRole;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
