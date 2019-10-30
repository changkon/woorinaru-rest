package woorinaru.rest.mapper.user;

import org.junit.jupiter.api.Test;
import woorinaru.core.model.management.administration.Resource;
import woorinaru.core.model.user.Admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminMapperTest {

    @Test
    public void testAdminModelIsMappedToDto() {
        // GIVEN
        Admin adminModel = new Admin();
        adminModel.setId(1);
        adminModel.setNationality("South Korea");
        adminModel.setEmail("random@test.com");
        adminModel.setName("Test name");
        adminModel.setSignUpDateTime(LocalDateTime.of(LocalDate.of(2019, 1, 1), LocalTime.of(10, 0, 0)));

        Resource resourceModel1 = new Resource();
        resourceModel1.setId(1);
        resourceModel1.setDescription("Resource description 1");

        Resource resourceModel2 = new Resource();
        resourceModel2.setId(2);
        resourceModel2.setDescription("Resource description 2");

        adminModel.setFavouriteResources(List.of(resourceModel1, resourceModel2));

        // WHEN
        AdminMapper mapper = AdminMapper.MAPPER;
        woorinaru.rest.dto.user.Admin adminDto = mapper.mapToDto(adminModel);

        // THEN
        assertThat(adminDto.getId()).isEqualTo(1);
        assertThat(adminDto.getNationality()).isEqualTo("South Korea");
        assertThat(adminDto.getEmail()).isEqualTo("random@test.com");
        assertThat(adminDto.getName()).isEqualTo("Test name");
        assertThat(adminDto.getSignUpDateTime()).isEqualTo(LocalDateTime.of(LocalDate.of(2019, 1, 1), LocalTime.of(10, 0, 0)));
        assertThat(adminDto.getFavouriteResources()).hasSize(2);
        assertThat(adminDto.getFavouriteResources()).contains(1, 2);
    }

    @Test
    public void testAdminDtoIsMappedToModel() {
        // GIVEN
        woorinaru.rest.dto.user.Admin adminDto = new woorinaru.rest.dto.user.Admin();
        adminDto.setId(1);
        adminDto.setNationality("South Korea");
        adminDto.setEmail("random@test.com");
        adminDto.setName("Test name");
        adminDto.setSignUpDateTime(LocalDateTime.of(LocalDate.of(2019, 1, 1), LocalTime.of(10, 0, 0)));

        adminDto.setFavouriteResources(List.of(1, 2));

        // WHEN
        AdminMapper mapper = AdminMapper.MAPPER;
        Admin adminModel = mapper.mapToModel(adminDto);

        // THEN
        assertThat(adminModel.getId()).isEqualTo(1);
        assertThat(adminModel.getNationality()).isEqualTo("South Korea");
        assertThat(adminModel.getEmail()).isEqualTo("random@test.com");
        assertThat(adminModel.getName()).isEqualTo("Test name");
        assertThat(adminModel.getSignUpDateTime()).isEqualTo(LocalDateTime.of(LocalDate.of(2019, 1, 1), LocalTime.of(10, 0, 0)));
        assertThat(adminModel.getFavouriteResources()).hasSize(2);
        assertThat(adminModel.getFavouriteResources()).anyMatch(r -> r.getId() == 1);
        assertThat(adminModel.getFavouriteResources()).anyMatch(r -> r.getId() == 2);
    }

}
