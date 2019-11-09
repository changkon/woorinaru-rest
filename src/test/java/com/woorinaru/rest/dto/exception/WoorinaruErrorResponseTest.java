package com.woorinaru.rest.dto.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WoorinaruErrorResponseTest {

    @Test
    public void testJsonIsMappedToWoorinaruErrorResponse() throws JSONException, JsonProcessingException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        String json = new JSONObject()
            .put("status", 404)
            .put("message", "test error message")
            .put("timeStamp", 11103)
            .toString();

        // WHEN
        WoorinaruErrorResponse errorResponseDto = mapper.readValue(json, WoorinaruErrorResponse.class);

        // THEN
        assertThat(errorResponseDto.getStatus()).isEqualTo(404);
        assertThat(errorResponseDto.getMessage()).isEqualTo("test error message");
        assertThat(errorResponseDto.getTimeStamp()).isEqualTo(11103);
    }

    @Test
    public void testWoorinaruErrorResponseDtoIsMappedToJson() throws JsonProcessingException, JSONException {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();

        WoorinaruErrorResponse errorResponse = new WoorinaruErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage("test error message");
        errorResponse.setTimeStamp(10000);

        // WHEN
        String json = mapper.writeValueAsString(errorResponse);
        JSONObject jsonObject = new JSONObject(json);

        // THEN
        assertThat(jsonObject.has("status")).isTrue();
        assertThat(jsonObject.getInt("status")).isEqualTo(400);
        assertThat(jsonObject.has("message")).isTrue();
        assertThat(jsonObject.getString("message")).isEqualTo("test error message");
        assertThat(jsonObject.has("timeStamp")).isTrue();
        assertThat(jsonObject.getLong("timeStamp")).isEqualTo(10000);
    }

}
