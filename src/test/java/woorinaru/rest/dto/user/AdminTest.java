package woorinaru.rest.dto.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminTest {

    @Test
    public void testJsonIsMappedToAdmin() throws JsonProcessingException, JSONException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        String json = new JSONObject()
            .put("name", "Test name")
            .put("nationality", "South Korea")
            .put("email", "random@test.com")
            .put("signUpDateTime", "2019-01-10 10:10:00")
            .toString();

        // WHEN
        Admin adminDto = mapper.readValue(json, Admin.class);

        // THEN
        assertThat(adminDto.getName()).isEqualTo("Test name");
        assertThat(adminDto.getNationality()).isEqualTo("South Korea");
        assertThat(adminDto.getEmail()).isEqualTo("random@test.com");
        assertThat(adminDto.getFavouriteResources()).isNullOrEmpty();
        assertThat(adminDto.getSignUpDateTime()).isEqualTo(LocalDateTime.of(LocalDate.of(2019, 1, 10), LocalTime.of(10, 10, 0)));
    }

    @Test
    public void testFavouriteResourcesIsMappedToAdminResource() throws JSONException, JsonProcessingException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        String json = new JSONObject()
            .put("favouriteResources", new JSONArray(List.of(1, 2, 3)))
            .toString();

        // WHEN
        Admin adminDto = mapper.readValue(json, Admin.class);

        // THEN
        assertThat(adminDto.getFavouriteResources()).hasSize(3);
        assertThat(adminDto.getFavouriteResources()).contains(1, 2, 3);
    }

}
