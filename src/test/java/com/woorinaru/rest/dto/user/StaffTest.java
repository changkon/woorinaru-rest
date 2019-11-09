package com.woorinaru.rest.dto.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import com.woorinaru.rest.dto.management.administration.Team;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StaffTest {

    @Test
    public void testJsonIsMappedToStaff() throws JsonProcessingException, JSONException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        String json = new JSONObject()
            .put("name", "Test name")
            .put("nationality", "South Korea")
            .put("email", "random@test.com")
            .put("signUpDateTime", "2019-01-10 10:10:00")
            .put("staffRole", "leader")
            .put("team", "design")
            .toString();

        // WHEN
        Staff staffDto = mapper.readValue(json, Staff.class);

        // THEN
        assertThat(staffDto.getName()).isEqualTo("Test name");
        assertThat(staffDto.getNationality()).isEqualTo("South Korea");
        assertThat(staffDto.getEmail()).isEqualTo("random@test.com");
        assertThat(staffDto.getFavouriteResources()).isNullOrEmpty();
        assertThat(staffDto.getSignUpDateTime()).isEqualTo(LocalDateTime.of(LocalDate.of(2019, 1, 10), LocalTime.of(10, 10, 0)));
        assertThat(staffDto.getStaffRole()).isEqualTo(StaffRole.LEADER);
        assertThat(staffDto.getTeam()).isEqualTo(Team.DESIGN);
    }

    @Test
    public void testStaffDtoIsMappedToJson() throws JsonProcessingException, JSONException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff();
        staff.setId(10);
        staff.setName("Test name");
        staff.setNationality("South Korea");
        staff.setEmail("random@test.com");
        staff.setFavouriteResources(Collections.emptyList());
        staff.setSignUpDateTime(LocalDateTime.of(LocalDate.of(2019, 1, 10), LocalTime.of(10, 10, 0)));
        staff.setTeam(Team.EDUCATION);
        staff.setStaffRole(StaffRole.TEACHER);

        // WHEN
        String json = mapper.writeValueAsString(staff);
        JSONObject jsonObject = new JSONObject(json);

        // THEN
        assertThat(jsonObject.has("id")).isTrue();
        assertThat(jsonObject.getInt("id")).isEqualTo(10);
        assertThat(jsonObject.has("name")).isTrue();
        assertThat(jsonObject.getString("name")).isEqualTo("Test name");
        assertThat(jsonObject.has("nationality")).isTrue();
        assertThat(jsonObject.getString("nationality")).isEqualTo("South Korea");
        assertThat(jsonObject.has("email")).isTrue();
        assertThat(jsonObject.getString("email")).isEqualTo("random@test.com");
        assertThat(jsonObject.has("favouriteResources")).isTrue();
        assertThat(jsonObject.getJSONArray("favouriteResources").length()).isEqualTo(0);
        assertThat(jsonObject.has("signUpDateTime")).isTrue();
        assertThat(jsonObject.getString("signUpDateTime")).isEqualTo("2019-01-10 10:10:00");
        assertThat(jsonObject.has("staffRole")).isTrue();
        assertThat(jsonObject.getString("staffRole")).isEqualTo("teacher");
        assertThat(jsonObject.has("team")).isTrue();
        assertThat(jsonObject.getString("team")).isEqualTo("education");
    }

    @Test
    public void testFavouriteResourcesIsMappedToAdminResource() throws JSONException, JsonProcessingException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        String json = new JSONObject()
            .put("favouriteResources", new JSONArray(List.of(1, 2, 3)))
            .toString();

        // WHEN
        Staff staffDto = mapper.readValue(json, Staff.class);

        // THEN
        assertThat(staffDto.getFavouriteResources()).hasSize(3);
        assertThat(staffDto.getFavouriteResources()).contains(1, 2, 3);
    }

}
