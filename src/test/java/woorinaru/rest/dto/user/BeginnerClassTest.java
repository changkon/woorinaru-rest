package woorinaru.rest.dto.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import woorinaru.rest.dto.management.administration.BeginnerClass;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BeginnerClassTest {

    @Test
    public void testJsonIsMappedToBeginnerClass() throws JSONException, JsonProcessingException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        String json = new JSONObject()
            .put("id", 1)
            .put("resources", new JSONArray(List.of(1, 2, 3)))
            .put("staff", new JSONArray(List.of(1, 4)))
            .put("students", new JSONArray(List.of(2, 3)))
            .put("event", 1)
            .toString();

        // WHEN
        BeginnerClass beginnerClassDto = mapper.readValue(json, BeginnerClass.class);

        // THEN
        assertThat(beginnerClassDto.getId()).isEqualTo(1);
        assertThat(beginnerClassDto.getResources()).contains(1, 2, 3);
        assertThat(beginnerClassDto.getStaff()).contains(1, 4);
        assertThat(beginnerClassDto.getStudents()).contains(2, 3);
        assertThat(beginnerClassDto.getEvent()).isEqualTo(1);
    }

    @Test
    public void testBeginnerClassDtoIsMappedToJson() throws JsonProcessingException, JSONException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        BeginnerClass beginnerClass = new BeginnerClass();
        beginnerClass.setId(5);
        beginnerClass.setEvent(1);
        beginnerClass.setResources(List.of(1, 2, 3));
        beginnerClass.setStaff(List.of(10, 11));
        beginnerClass.setStudents(List.of(1, 4, 5));

        // WHEN
        String json = mapper.writeValueAsString(beginnerClass);
        JSONObject jsonObject = new JSONObject(json);

        // THEN
        assertThat(jsonObject.has("id")).isTrue();
        assertThat(jsonObject.getInt("id")).isEqualTo(5);
        assertThat(jsonObject.has("event")).isTrue();
        assertThat(jsonObject.getInt("event")).isEqualTo(1);
        assertThat(jsonObject.has("resources")).isTrue();
        assertThat(jsonObject.getJSONArray("resources").length()).isEqualTo(3);
        assertThat(jsonObject.getJSONArray("resources").getInt(0)).isEqualTo(1);
        assertThat(jsonObject.getJSONArray("resources").getInt(1)).isEqualTo(2);
        assertThat(jsonObject.getJSONArray("resources").getInt(2)).isEqualTo(3);
        assertThat(jsonObject.has("staff")).isTrue();
        assertThat(jsonObject.getJSONArray("staff").length()).isEqualTo(2);
        assertThat(jsonObject.getJSONArray("staff").getInt(0)).isEqualTo(10);
        assertThat(jsonObject.getJSONArray("staff").getInt(1)).isEqualTo(11);
        assertThat(jsonObject.has("students")).isTrue();
        assertThat(jsonObject.getJSONArray("students").length()).isEqualTo(3);
        assertThat(jsonObject.getJSONArray("students").getInt(0)).isEqualTo(1);
        assertThat(jsonObject.getJSONArray("students").getInt(1)).isEqualTo(4);
        assertThat(jsonObject.getJSONArray("students").getInt(2)).isEqualTo(5);
        assertThat(jsonObject.has("grade")).isTrue();
        assertThat(jsonObject.getString("grade")).isEqualTo("beginner");
    }

}
